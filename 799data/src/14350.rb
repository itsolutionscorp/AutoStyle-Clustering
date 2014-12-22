#!/usr/bin/env ruby




#def combine_anagrams(words)

#	sortedStringsArray = Array.new

#	i = 0
#	while i < words.size
	
	
#		splitter = Array.new
#		splitter = words[i].split(//) #split the string into individual characters, and place those chars into elements of array
#		
#		sortedString = splitter.sort.join("") #sort the chars in the array, then rejoin them all into a new string
		#puts sortedString
#		sortedStringsArray[i] = sortedString
	
#		i = i+1
#	end
	#puts sortedStringsArray
	
#	anagramArray = Array.new
#	finalArray = Array.new
	
#	i = 0
#	while i < sortedStringsArray.size
#		j = 1
#		anagramArray.push(words[i])
#		while j < sortedStringsArray.size
#			if sortedStringsArray[i].eql?(sortedStringsArray[j])
#				anagramArray.push(words[j])
#				sortedStringsArray.delete_at(j)
#			end
#			j = j+1
#		end
#		sortedStringsArray.delete_at(i)
#		finalArray[i] = anagramArray
#		i = i+1
#	end
	
#	puts finalArray.uniq
	
#end







# Solves Stanford's SAAS anagram question
# http://spark-university.s3.amazonaws.com/berkeley-saas/homework/hw1.pdf
# Question 3
#def combine_anagrams(words)  

# Set up a hash to hold the groups:  
#   Keys will be the sorted anagram string  
#   Values will be the array of anagrams for that string  
# Hash works well here because it's a fast lookup on  
# the key, even when the words array gets really long.  
#groups = {}  

# Iterate over the words list  
#words.each do |word|
   
# Find the anagram key, which is the sorted lowercase letters    
# "ElliottWood" becomes "deillooottw"    
#characters = word.downcase.chars.sort.join    

# Key into the groups hash for that anagram key    
# Set it to an empty array if it doesn't exist,    
# then append the current word to that array.    
#groups[characters] ||= []    
#groups[characters] << word  

#end  

# The Hash#values fetches the arrays without  
# the keys, which is the desired return format.  

#groups.values

#end










def anagrams?(w1, w2)  
	w1.downcase.split('').sort == w2.downcase.split('').sort
end

def combine_anagrams(words)  
	ret=[]  
	while words.length>0 do    
	i=0    
	temp=[words[i]]    
	j=i+1    
		while j<words.length do      
			if anagrams?(words[i],words[j])      
			temp << words[j]      
			words.delete_at(j)      
			else      
			j=j+1      
			end    
		end    
		ret<< temp    
		words.delete_at(i)  
	end  
	ret
end



#combine_anagrams(['poop'])
p combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream'])
	# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

	
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
