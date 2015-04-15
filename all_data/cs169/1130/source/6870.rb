def combine_anagrams(words)

  sw = Hash.new {|h,k| h[k] = []}
  words.each do |word|
    sw[word.downcase.split(//).sort.join]<<word
  end  
  
  return sw.values

end

words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream']
#p combine_anagrams(words)
#p words.methods.sort

words = ['A', 'a']
p combine_anagrams(words)

words = ['HeLLo', 'hello']
p combine_anagrams(words)
