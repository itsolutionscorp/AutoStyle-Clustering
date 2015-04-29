def combine_anagrams(words)
  result_hash = words.group_by { |a| a.downcase.chars.sort.to_s }
  return result_hash.values
end