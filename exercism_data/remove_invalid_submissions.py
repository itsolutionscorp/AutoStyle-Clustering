import os
from collections import Counter
import shutil

mapfile = open("mapping.csv", "w")

count = 0
for f in os.listdir('word-count'):
	count+=1
print 'starting with submissions: ' + str(count)


if not os.path.isdir('word-count-filtered'):
	os.mkdir('word-count-filtered')
else:
	shutil.rmtree('word-count-filtered')
	os.mkdir('word-count-filtered')

if not os.path.isdir('src'):
	os.mkdir('src')
else:
	shutil.rmtree('src')
	os.mkdir('src')

for f in os.listdir('word-count'):
	data = open("word-count/" + f, "r").read()
	if not (data.count('def') > 2 or data.find('def word_count') == -1 or data.find('def initialize') == -1):
		shutil.copyfile('word-count/'+f, 'word-count-filtered/'+f)

count = 0
for f in os.listdir('word-count-filtered'):
	submission_id = f.strip(".rb")  
	index_id = len(os.listdir('src'))
	shutil.copyfile('word-count-filtered/'+f, 'src/'+str(index_id)+'.rb')
	mapfile.write(str(submission_id) + ' : ' + str(index_id) + '\n')
	count += 1

print 'filtered to submissions: ' + str(count)
			
