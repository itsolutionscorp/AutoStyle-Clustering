import os
from collections import Counter
import shutil
import sys
import argparse

# for example call this script on hamming from exercism_data directory like: python remove_invalid_submissions.py "ruby/hamming/" "filtered-submissions/" "compute"  
parser = argparse.ArgumentParser(description='Filter and clean data and put in src directory')
parser.add_argument('data_directory', help='Directory for original data')
parser.add_argument('filtered_submissions', help='Directory to store filtered data')
parser.add_argument('method', help='Method to extract')


args = parser.parse_args()
data_directory = args.data_directory
filtered_submissions =args.filtered_submissions
method = args.method

mapfile = open("mapping.csv", "w")

count = 0
for f in os.listdir(data_directory):
	count+=1
print 'starting with submissions: ' + str(count)


if not os.path.isdir(filtered_submissions):
	os.mkdir(filtered_submissions)
else:
	shutil.rmtree(filtered_submissions)
	os.mkdir(filtered_submissions)

if not os.path.isdir('src'):
	os.mkdir('src')
else:
	shutil.rmtree('src')
	os.mkdir('src')

for f in os.listdir(data_directory):
	data = open(data_directory + f, "r").read()
	#if not (data.count('def') == 1 or data.find('def word_count') == -1 or data.find('def initialize') == -1):
	if data.count('def') == 1 and (data.find('def self.' + str(method)) != -1 or data.find('def ' + str(method)) != -1): 
		data = data.replace('def self.' + str(method), 'def ' + str(method))
		num_ends_to_strip = data.count('class')
		data = data[data.find('def ' + str(method)):]
		for i in range(num_ends_to_strip):
			data = data[:data.rfind('end')]
		data = data.rstrip()
		out = open(filtered_submissions+f, "w+")
		out.write(data)

count = 0
for f in os.listdir(filtered_submissions):
	submission_id = f.strip(".rb")  
	index_id = len(os.listdir('src'))
	shutil.copyfile(filtered_submissions+f, 'src/'+str(index_id)+'.rb')
	mapfile.write(str(submission_id) + ' : ' + str(index_id) + '\n')
	count += 1

print 'filtered to submissions: ' + str(count)
			
