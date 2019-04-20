"""
自动解压apk，批量使用jadx进行反编译，结果代码汇总
"""
import os
import zipfile

temp_output_dir = os.path.join(os.getcwd(), "temp\\")  # 解压后文件的临时存储路径
last_output_dir = os.path.join(os.getcwd(), "output\\")  # 最后汇总的输出目录


# 判断目录是否存在，不存在则创建
def is_dir_existed(path, mkdir=True):
    if mkdir:
        if not os.path.exists(path):
            os.makedirs(path)
    else:
        return os.path.exists(path)


# 获取目录下的所有文件路径
def fetch_all_file(file_dir):
    return list(map(lambda x: os.path.join(file_dir, x), os.listdir(file_dir)))


# 解压文件到特定路径中
def unzip_file(file_name, output_dir):
    print("开始解压文件...")
    f = zipfile.ZipFile(file_name, 'r')
    for file in f.namelist():
        f.extract(file, os.path.join(os.getcwd(), output_dir))
    print("文件解压完毕...")


if __name__ == '__main__':
    # 相关文件夹初始化
    is_dir_existed(temp_output_dir)
    is_dir_existed(last_output_dir)
    # 判断目录中是否存在apk文件
    file_path_list = fetch_all_file(os.getcwd())
    apk_path = ''
    for file_path in file_path_list:
        if file_path.endswith('.apk') or file_path.endswith('.zip'):
            apk_path = file_path
    if apk_path == '':
        print("当前目录中没有apk文件，请检查后重试！")
        exit(0)
    else:
        # 修改后缀名
        print("修改文件后缀名")
        os.rename(apk_path, apk_path.replace(".apk", ".zip"))
        # 解压
        unzip_file(apk_path.replace(".apk", ".zip"), 'temp\\')
        # 获取所有dex后缀的文件路径
        file_path_list = fetch_all_file(temp_output_dir)
        dex_path_list = []  # dex后缀文件路径列表
        for file_path in file_path_list:
            if file_path.endswith(".dex"):
                dex_path_list.append(file_path)
        # 批量反编译到特定文件
        print("开始反编译所有dex文件")
        for dex in dex_path_list:
            os.system(
                "jadx -d {0} {1}".format(os.path.join(last_output_dir, dex.split("\\")[-1][:-4]), dex))
        print("所有dex文件反编译完毕！")
