def combine_anagrams(words)
  out = Hash.new
  words.each { |word|
    fixedword = word.downcase.split(//).sort
    if out.has_key? fixedword
      out[fixedword] << word
    else
      out[fixedword] = [word]
    end
  }
  out.values
end

#print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
