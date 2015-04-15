def combine_anagrams(words)
  result = [];
  words.each_index do |i|
    word = words[i].downcase.chars.sort.join
    result << words.select{|w|
      word == w.downcase.chars.sort.join
    }
  end
  
  return result.uniq
end

puts combine_anagrams(['Cars', 'CaRs','for', 'potatoes', 'raCs', 'four','scar', 'creams', 
'scream']).inspect