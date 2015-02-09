#Part 3  - Anagrams 
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
# input: => ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

def word_index(word)
  word.downcase.chars.sort.join
end

def index_match(word1,word2)
  word_index(word1) == word_index(word2) 
end

def combine_anagrams(words)
  combined = Array.new(0)
  words.each do |word|
    found = false 
    combined.each do |combination| 
      #combined will always have at least one member
      if index_match(combination[0],word) 
        found = true 
        combination << word
      end
    end
    if !found 
      combination = Array.new
      combination << word 
      combined << combination 
    end 
  end
  return combined 
end