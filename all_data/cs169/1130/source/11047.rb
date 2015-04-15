#part3.rb



def combine_anagrams(words)
  #checks input correctness
  raise ArgumentError, 'Argument is not an Array' unless words.class == Array
  #the array the anagrams get grouped into
  angroups = Array.new
  if words.length < 2
    return words
  end
  for i in (0...words.length).to_a
    angroups[i] = Array.new
    for j in words
      if words[i].downcase.split('').sort == j.downcase.split('').sort
        angroups[i] << j
      end
    end
  end
  return angroups.uniq
end

a = combine_anagrams(['a', 'a'])
b = combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
puts a
puts a.length 
puts a.class
# # puts combine_anagrams(['hey', 'HEY', 'ehy', 'jack'])
# # puts combine_anagrams(['hey', 'HEY', 'ehy', 'jack']).length


