#-*- coding:UTF-8 -*-
"""
refresh README.md
add index of notes to README.md 

"""

import os
import re

exclude_dirs = [".git", "figures"]
exclude_files = ["README.md", "test.md"]

front = """# our-CS-notes
CS notes from :yellow_heart:[Sakana](https://github.com/NinomiyaSakana) & [Kayasu](https://github.com/Li-Huakang) :yellow_heart:

自学笔记 不定期更新
```bash
# 更新目录索引README.md
python contents.py
```

```md
笔记目录索引结构
# our-CS-notes
## second-level directory name
### third-level directory name
...
[markdown file title name](/path/name)
```

"""


def walktree(top, rst):
    flag = False
    with os.scandir(top) as entries:
        for entry in entries:
            if  entry.is_dir():
                if entry.name in exclude_dirs:
                    continue
                #dir
                dir = entry.name
                rst[dir]={}
                path = os.path.join(top, entry.name)
                hasmd = walktree(path, rst[dir])
                flag = hasmd or flag
                if not hasmd:
                    rst.pop(dir)
            else:
                #file
                if entry.name.endswith('.md'):
                    rst[entry.name] = None
                    flag = True or flag
                else:
                    flag = False or flag
    return flag


def gen(res, level, f, path=''):
    for key in sorted(res):
        val = res[key]
        if(val==None):#.md
            if key in exclude_files:
                continue
            str = '[' + key.strip('.md') + ']' + '(' + path + '/' + key.replace(' ', '%20') + ')' + '\n' + '\n'
            f.write(str)
        else:#dir
            if level==2:
                str = '\n'+'#'*level+' '+key+'\n'
            else:
                str = '#'*level+' '+key+'\n'
            f.write(str)
            gen(val, level+1, f, path+'/'+key)

def main():
    basepath = './'
    res = {}
    walktree(basepath, res)
    with open('README.md', 'w+') as f:
        f.write(front)
        gen(res,2,f)

if __name__ == "__main__":
    main()