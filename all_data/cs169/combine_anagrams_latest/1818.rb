
# anagrams

def anagram(string)
  string.downcase.chars.sort.join
end

# input: 
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  @groups = {}
  words.each do |word| 
    if (! @groups[anagram(word)]) 
      @groups[anagram(word)]=[word] 
    else 
      @groups[anagram(word)] << word
    end
  end  
  return @groups.values
end

#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).to_s

