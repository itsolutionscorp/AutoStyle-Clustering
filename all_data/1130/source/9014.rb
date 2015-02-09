def anagrams?(s1, s2)
  s1.downcase.chars.to_a.sort == s2.downcase.chars.to_a.sort
end

def find_anagrams(word, words)
  a = []
  words.each {|w| a << w if anagrams?(word, w) }
  a
end

def combine_anagrams(words)
  anagrams = []
  words.each do |word|
	anagrams << find_anagrams(word, words) unless anagrams.flatten.include? word
  end
  anagrams
end