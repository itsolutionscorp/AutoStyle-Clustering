def combine_anagrams(words)
  output = {}
  
  words.each do |w|
    reversed_word = w.downcase.split(//).sort.join
    
    output[reversed_word] ||= []
    output[reversed_word] << w
  end
  
  return output.values
end