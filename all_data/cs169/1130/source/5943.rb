def combine_anagrams(words)
  values = []
  output = []
  words.each{ 
    |value| 
    code = value.downcase.sum
    if values.include?(code)
      pointer = values.index(code)
      output[pointer] += [value]
    else
      values += [code]
      pointer = values.index(code)
      output[pointer] = [value]
    end
  }
  
  return output
end