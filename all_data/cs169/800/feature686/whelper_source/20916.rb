def combine_anagrams(words)
  anagram = Hash.new
  words.each do |w|
    key = w.to_s.chars.sort.join
    anagram[key] ||= []
    (anagram[key] << w.to_s)
  end
  anagram.values.sort
end

