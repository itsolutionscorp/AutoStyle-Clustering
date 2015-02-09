

def combine_anagrams(words)
  anagram = Hash.new { |hash, key| hash[key] = [] }
  words.each do |word|
    anagram[word.downcase.split('').sort.join('')] << word
  end
  anagram.values
end
