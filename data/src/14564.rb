def combine_anagrams(words)
  result = Array.new(0)
  words.each do |w1|
    anagram = []
    words.each do |w2|
      (anagram << w2) if (w2.downcase.chars.sort == w1.downcase.chars.sort)
    end
    (result << anagram)
  end
  return result.uniq
end