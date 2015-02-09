def combine_anagrams(words)
  sorted = words.map { |word| word.downcase.chars.sort { |a, b| a.casecmp(b) }.join }
  indexed = Hash.new(Array.new)
  sorted.each_with_index { |elem, i| indexed[elem] += [i] }
  result = []
  indexed.each_value { |val| result.push(val.map { |index| words[index] }) }
  result
end

