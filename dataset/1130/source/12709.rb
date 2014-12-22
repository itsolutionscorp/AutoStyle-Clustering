#    Part 3: anagrams
#    An anagram is a word obtained by rearranging the letters of another word.  For 
#    example, "rats", "tars" and "star" are an anagram group because they are made up of the same 
#    letters.
#    Given an array of strings, write a method that groups them into anagram groups and returns 
#    the array of groups.  Case doesn't matter in classifying string as anagrams (but case should be 
#    preserved in the output), and the order of the anagrams in the groups doesn't matter.
#    Example:
#    # input: 
#    ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#    #  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], 
#    ["creams", "scream"]]
#    # HINT: you can quickly tell if two words are anagrams by sorting their
#    #  letters, keeping in mind that upper vs lowercase doesn't matter
#    def combine_anagrams(words)
#      #   <

def combine_anagrams(wordArray)
  
  
  sortedCharsHash = Hash.new
  groupedArray = Array.new
  n = 0
  
  wordArray.each do |word|
    
    #if freq hash not in freq hash array, put in there and add to grouped array
    #if freq hash in freq hash array, iterate through items until match and put in there
    
    sortedChars = word.downcase.split(//).sort.join
    if sortedCharsHash[sortedChars]
      groupedArray[sortedCharsHash[sortedChars].to_i] << word
    else
      sortedCharsHash[sortedChars] = n
      n = n +1
      groupedArray << [word]
    end
  end
  
  return groupedArray
  
  
end



#def char_frequency_hash(string)
#  # your code here
#  words = Hash.new
#  
#  chars = string.downcase.split(//i)
#  chars.each do |char|
#    if (char.match(/\w/i))
#      
#      if (! words.include? char)
#        words[char] = 1
#      else
#        words[char] = words[char]+1
#      end
#    end
#  end
#  
#  return words
#  
#end




# TEST
print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect  # => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"]