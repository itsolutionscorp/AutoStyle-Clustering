def combine_anagrams(words)
  answer = words.group_by { |word| word.downcase.chars.sort.uniq.join }
  answer.values
end

