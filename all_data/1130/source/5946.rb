def combine_anagrams(words)
  result = Hash.new([])
  words.each do |word|
    key = word.downcase
    key = key.chars.sort.join
    result[key] = result[key]+[word]
  end
  return result.values
end
#array=['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
array=[]
puts combine_anagrans(array)
