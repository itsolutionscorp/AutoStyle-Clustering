def combine_anagrams(words)
  anagrams = {}
  words.each do |word|
    anagram = get_anagram(word)
    if anagrams.has_key?(anagram)
      anagrams[anagram] << word
    else 
      anagrams[anagram] = [word]
    end
  end
  return anagrams.values
end

def get_anagram(word)
  return word.downcase.split('').sort.join
end