# import os
# files = [f for f in os.listdir(".") if f != "script.py" and f!="." and f!=".." and f!="untarred" and f != "ucb.py" and f!="validguesses.txt" and f!= ".DS_Store"]
# failure = 0
# for i,f in enumerate(files):
# 	try:
# 		openfile = open(f, "r+")
# 		lines = openfile.readlines()
# 		if lines[0].strip("\r\n") != "def num_common_letters(goal_word, guess):":
# 			temp = [a.strip("\r\n") for a in lines]
# 			start = temp.index("def num_common_letters(goal_word, guess):")
# 			end = temp.index("def make_word_master(goal_word):") 
# 			newlines = lines[start:end]
# 			open(f, "w").close()
# 			openfile = open(f, "w")
# 			openfile.writelines(newlines)
# 			openfile.close()
# 			print "done " + f
# 	except Exception as e:
# 		failure+=1
# 		print "failed " + f + "  " + str(e)

# print "TOTAL: " + str(failure)


import os
files = [f for f in os.listdir(".") if f != "script.py" and f!="." and f!=".." and f!="untarred" and f != "ucb.py" and f!="validguesses.txt" and f!= ".DS_Store"]
failure = 0
for i,f in enumerate(files):
	try:
		openfile = open(f, "r+")
		lines = openfile.readlines()
		if lines[0].strip("\r\n") != '    """Returns the number of letters in goal_word that are also in guess.':
			temp = [a.strip("\r\n") for a in lines]
			start = temp.index('    """Returns the number of letters in goal_word that are also in guess.')
			end = temp.index('"""') 
			newlines = lines[start:end]
			open(f, "w").close()
			openfile = open(f, "w")
			openfile.writelines(newlines)
			openfile.close()
			print "done " + f
	except Exception as e:
		failure+=1
		print "failed " + f + "  " + str(e)

print "TOTAL: " + str(failure)
