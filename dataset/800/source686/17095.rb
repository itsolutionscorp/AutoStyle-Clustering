def combine_anagrams(words)
  
  array_full = Array.new
  
  words.each do | word |
    array = Array.new
    array << word
    words.each do | word_inner |
      if word.split(//).sort.join === word_inner.split(//).sort.join
          if ! array.include? word_inner 
            array << word_inner
          end
      end      
    end
    if ! array_full.include? array.sort
      array_full << array.sort
    end
  end
  
  return array_full

end


print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
