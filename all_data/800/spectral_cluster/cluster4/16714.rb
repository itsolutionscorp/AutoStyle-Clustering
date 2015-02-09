def combine_anagrams(words)
  out_array = Array.new(0)
  words.each do |w1|
    temp = []
    words.each do |w2|
      if (w2.downcase.split(//).sort == w1.downcase.split(//).sort)
        temp.push(w2)
      end
    end
    out_array.push(temp)
  end
  return out_array.uniq
end

print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']) 
 