#!/usr/bin/ruby

def combine_anagrams(words)
#	<YOUR CODE HERE>

h = Hash.new()
j = Array.new()
i = 0


a = words
b = words
c = Array.new()
d = Array.new()
e = Array.new()
output = Array.new()

k = 0
a.each do |i|
	b.each do |j|
		if i.split(//).sort.join == j.split(//).sort.join 
		  then c.push j
		end
    end
    if c.length > 1
        then d.push c.uniq
    elsif c.length == 1
        d.push c
    end
    c = []
end
#d = c.uniq

#d.each do |i|
#	b.each do |j|
#		if i.split(//).sort.join == j.split(//).sort.join && i != j
#		  then c.push i	
#		end
#    end
#    if 

if d.length == d.join.length
  then output = d 
else
  output = d.uniq
end

#puts "Output: " + output.inspect
#puts "d: " + d.uniq.inspect

return output.uniq
end

#combine_anagrams(['a', 'a', 'b', 'b', 'c', 'd'])

#combine_anagrams(["cars", "for", "potatoes", "racs", "four","scar", "creams", "scream"])
#combine_anagrams(["cars", "for", "potatoes", "racs", "four","scar", "creams", "scream"])
