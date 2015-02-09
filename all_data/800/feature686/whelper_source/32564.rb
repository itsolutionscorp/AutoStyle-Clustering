def combine_anagrams(words)
  dict = Hash.new { [] }
  words.each { |w| dict[w.chars.sort.join] = (dict[w.chars.sort.join] << w) }
  result = []
  dict.each { |key, value| result = (result << value) }
  result
end

