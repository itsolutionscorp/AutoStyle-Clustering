#!/usr/bin/ruby
#require ‘jcode’

#step 0 declare vars
v1 =""
v2 = ""
array1=[]
array2=[]

#step 1 I can read in argvs
v1 = ARGV[0]
v2 = ARGV[1]

array1 = v1.chars
array2 = v2.chars

#step 3 I need 2 loops for this
x = 0
theHammingDistance = 0
same = 0
while x < v1.length do
if array1[x] == array2[x]
same += 1
else
theHammingDistance += 1
end
x+=1  #move to compare the next pair of letters
end


return theHammingDistance
