def combine_anagrams(words)
  anagrams = Array.new(0)
  words.map do |word|
    if anagrams.any? {|anagram| anagram[0].downcase.chars.sort == word.downcase.chars.sort} 
        anagrams.map do |anagram|
          if anagram[0].downcase.chars.sort == word.downcase.chars.sort
            anagram << word
          end
        end
    else
      anagrams << [word]
    end
  end
  return anagrams
end

# print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream'])
# 
# gets;