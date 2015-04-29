def anagram_form(word)
  word.chars.sort_by(&:downcase).join
end

def combine_anagrams(words)
  uniq_anagrams = []
  anagrams = []
  words.each { |word| uniq_anagrams.push(anagram_form(word)) }
  uniq_anagrams.uniq!
  uniq_anagrams.each do |anagram|
    anagrams.push(words.select { |word| (anagram_form(word) == anagram) }.uniq)
  end
  anagrams.uniq
end

