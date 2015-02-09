#An anagram is a word obtained by rearranging the letters of another word.  For 
#example, "rats", "tars" and "star" are an anagram group because they are made up of the same 
#letters.
#Given an array of strings, write a method that groups them into anagram groups and returns 
#the array of groups.  Case doesn't matter in classifying string as anagrams (but case should be 
#preserved in the output), and the order of the anagrams in the groups doesn't matter.
#Example:
## input: 
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
##  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], 
#["creams", "scream"]]
## HINT: you can quickly tell if two words are anagrams by sorting their
##  letters, keeping in mind that upper vs lowercase doesn't matter
#def combine_anagrams(words)
#  #   <YOUR CODE HERE>
#end

def combine_anagrams(words)
  result = {}
  words.each do |word|
	sortedWord = word.downcase.split(//).sort.join
	puts sortedWord
	if result.has_key?(sortedWord)
		result[sortedWord] = result[sortedWord] + [word]
	else
		result[sortedWord] = [word]
	end
  end
  return result.values

end

#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] )