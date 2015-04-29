import os

"""
snippets of code to paste into terminal to shortlist/clean data
"""

# remove submissions with helper functions
for f in os.listdir("."):
	total = 0
	ff = open(f)
	lines = ff.readlines()
	for line in lines:
		if "def" in line:
			total+=1
	if total != 1:
		os.remove(f)

# remove empty lines in submissions
files = os.listdir(".")
for i,f in enumerate(files):
	ff = open(f)
	lines = ff.readlines()
	total = 0
	lines2 = [value for value in lines if (value.lstrip("\t").lstrip(" ") != "\n" and value.lstrip("\t").lstrip(" ") != "\r\n")]
	lines2 = [value for value in lines2 if (value.lstrip("\t").lstrip(" ")[0] != "#")]
	if len(lines) != len(lines2):
		open(f, "w").close()
		open(f, "w").writelines(lines2)
	print i, len(lines2)


# rename files from 0 to n
files = os.listdir(".")
for i,f in enumerate(files):
	os.rename(f, str(i)+".py")