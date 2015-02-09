def combine_anagrams(words)
  anagram = {}
  words.each do |word|
    gram = word.chars.sort_by(&:downcase).join
    anagram[gram] ? (anagram[gram].push(word)) : (anagram[gram] = [word])
  end
  anagram.values
end