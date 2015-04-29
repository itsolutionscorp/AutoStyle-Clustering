def combine_anagrams(words)
  output = Array.new
  output2 = Array.new
  output3 = Array.new
  words.each_index {|ind| output.push(words[ind].downcase.split(//).sort)}
    #w_array = Array.new
    #aWord.each_byte {|x| w_array.push(x)}
 output.each_index {|ind|
  output2 = []
  output.each_index{ |j| 
    if output[ind]==output[j] then output2.push(words[j]) end}
  output3.push(output2)
  }
 
 return output3.uniq
end

puts combine_anagrams(['cars','for','potatoes','racs','four','scar','creams','scream'])
