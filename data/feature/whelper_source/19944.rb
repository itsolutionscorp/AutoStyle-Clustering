def combine_anagrams(words)
  temp = Hash.new
  words.group_by { |w| w.downcase.chars.sort.join }.values
end

