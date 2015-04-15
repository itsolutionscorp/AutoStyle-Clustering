input1 = ['a', 'A', 'Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

def combine_anagrams(words)
  if words.size == 0
    print "Array is Empty"
    return []
  end
  
  output = {}
  result = []
  words.each do |word|
    str = [word.chars.sort_by(&:downcase).join].to_s.downcase
    if output.has_key?(str)  
      output[str] << word
    else
      output[str] = [word]
    end
  end

  output.each do |key, value|
    result << output[key]
  end 
  
  return result
end

print combine_anagrams(input1)
