def combine_anagrams(words)
  f = words.group_by { |z| z.downcase.split("").sort.to_s }.values
end

