import os
files = [f for f in os.listdir(".") if f != "script.py" and f!="." and f!=".." and f!="untarred"]
for i,f in enumerate(files):
	os.system("tar -C ./untarred -zxvf "+f)
	os.system("mv ./untarred/hw4.py ./untarred/"+str(i)+".py")

