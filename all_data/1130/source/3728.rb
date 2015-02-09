
def combine_anagrams(words)
  #   <YOUR CODE HERE>
  h1 = Hash.new
  for word in words
    a = word.downcase.split('').sort.join
    puts a
    if h1.has_key? a then
      h1[a]<<word
    else
      h1[a] = [word]
    end
  end
  #puts h1
  return h1.values
end

#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] )