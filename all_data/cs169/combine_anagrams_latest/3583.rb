def combine_anagrams(words) 
return words.group_by { |x| x.chars.map(&:downcase).sort }.values
end 
