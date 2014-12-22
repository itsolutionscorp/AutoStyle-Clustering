
def combine_anagrams(words)
  output_array = Array.new(0)
  words.each do |w1|
    temp_array = []
    words.each do |w2|
      if (w2.downcase.split(//).sort == w1.downcase.split(//).sort)
        temp_array.push(w2)
      end
    end
    output_array.push(temp_array)
  end
  return output_array.uniq
end

## --- Test data ---
# puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four', 'Scar', 'creams', 'Scream']).to_s