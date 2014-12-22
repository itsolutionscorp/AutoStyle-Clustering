# Ananagrams use Hash as Indexed array via normalizatoin

def combine_anagrams(words)
   anagrams = Hash.new()
   words.each { |word|  
      word_key = word.downcase.split('').sort.join
      if anagrams.has_key?(word_key) 
           anagrams.store( word_key, anagrams.fetch(word_key).push(word))
      else
           anagrams.store( word_key, Array.new(1,word))
      end
   }
   anagrams.values  
end

# p combine_anagrams(['cars'])
# puts '----'
# p combine_anagrams(['cars', 'for', 'potatoes', 'racs'])