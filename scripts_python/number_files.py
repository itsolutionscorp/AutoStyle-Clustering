import os

for f in os.listdir(os.getcwd()):
    os.rename(f, f + ".temp")

i = 0
for f in os.listdir(os.getcwd()):
    os.rename(f, str(i))
    i += 1
