def combine_anagrams(words)
  anagrams = {}
  words.each do |word|
    anagram = word.downcase.gsub(/\W/, '').split(//).sort.join
    unless anagram.match(/\w/).nil? # Ignore punctuation (no word chars)
      if anagrams.has_key?(anagram)
        anagrams[anagram].push(word)
      else
        anagrams[anagram] = [word]
      end
    end
  end
  anagrams.values
end