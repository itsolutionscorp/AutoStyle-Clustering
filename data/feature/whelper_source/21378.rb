def combine_anagrams(words)
  groups = Hash.new { |hash, key| hash[key] = Array.new }
  words2 = words.map { |word| word.downcase.chars.sort.join }
  words2.each_index { |i| groups[words2[i]].push(words[i]) }
  groups.values
end

