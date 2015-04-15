def combine_anagrams(words)
  hash_symb = Hash.new
  words.each do |word|
    symb = word.downcase.split(//).sort.join
    temp_array = hash_symb[symb.to_sym]
    if temp_array.nil?
      hash_symb.store(symb.to_sym, Array.new([word]))
    else
      hash_symb.store(symb.to_sym, temp_array.concat([word]))
    end
  end
  hash_symb.values
end