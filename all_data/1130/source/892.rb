# An anagram is a word obtained by rearranging the letters of another word. For example, "rats", 
# "tars" and "star" are an anagram group because they are made up of the same letters.
# Given an array of strings, write a method that groups them into anagram groups and returns the 
# array of groups. Case doesn't matter in classifying string as anagrams (but case should be 
# preserved in the output), and the order of the anagrams in the groups doesn't matter.

# HINT: you can quickly tell if two words are anagrams by sorting their # letters, keeping in mind 
# that upper vs lowercase doesn't matter

def combine_anagrams(words)
	list = {}
   for word in words
      letters = word.downcase.scan(/./).sort.join
      if list[letters] == nil
         list[letters] = [word]
      else
         list[letters] << word
      end
   end
   
   groups = []
	list.each do |letters,words|
		groups << words
	end
	
	return groups
end

if __FILE__ == $0
   words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
   print words.inspect + "\n"
   groups = combine_anagrams(words)
   print groups.inspect + "\n"
   # output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
end
