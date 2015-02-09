def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |word|
    signature = word.downcase.chars.sort.join
    if anagrams[signature] == nil
      anagrams[signature] = [word];
    else
      anagrams[signature].push word
    end
  end
  anagrams.values;
end



#puts combine_anagrams(['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
