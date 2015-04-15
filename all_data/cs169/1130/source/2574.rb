def combine_anagrams(words)
  if words.nil?
    Array.new
  else
   (words.group_by {|w| w.downcase.chars.sort.join }).values
  end
end

