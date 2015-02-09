def combine_anagrams(words)
  result = Array.new
  0.upto((words.length - 1)) do |i|
    anagrams = words.each.map { |word| word if are_anagram?(words[i], word) }.compact
    (result << anagrams) unless result.include?(anagrams)
  end
  result
end