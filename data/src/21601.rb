def combine_anagrams(words)
  anagram = Hash.new
  words.each do |element|
    anagram[element.downcase.chars.sort.join] ||= []
    (anagram[element.downcase.chars.sort.join] << element)
  end
  m = []
  anagram.each { |key, value| (m << value) }
  m
end