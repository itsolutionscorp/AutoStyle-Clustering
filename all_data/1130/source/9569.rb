def combine_anagrams(words)
  anagrams = {}
  words.each do |word|
    anagram = word.downcase.split('').sort.join('')
    anagrams[anagram] = (anagrams[anagram] || []).push(word)
  end
  anagrams.map(&:last)
end
