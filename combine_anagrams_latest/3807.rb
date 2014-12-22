
def combine_anagrams(words)
  #   <YOUR CODE HERE>
  h = {}
  words.each do |word|
    sorted_word = word.downcase.scan(%r(\w)).sort.join
    if h.key?(sorted_word)
      h[sorted_word] = h[sorted_word] + [word]
    else
      h[sorted_word] =  [word]
    end   
  end
 
  result = []
  h.each_pair do |key, val|     
    result = result + [val]
  end
  
  result
   
end

#input = ['Cars', 'for', 'Cars','cars',  'potatoes', 'racs', 'four','scar', 'creams', 'scream']

#x = combine_anagrams(input)
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
