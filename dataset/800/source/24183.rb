def combine_anagrams(words)
  hash = Hash.new()
  
  index = 0
  words.each do |word|
    sorted = word.downcase.chars.sort.join
    if hash.has_key?(sorted) then hash[sorted].insert(0, index) else hash[sorted] = Array.[](index) end
    index = index + 1
  end
  
  answer = Array.[]
  hash.each {|key, value| 
  set = Array.[]
  value.each do |place|
    set.insert(0, words[place])
  end
  answer.insert(0, set)
  }
  
  return answer
end

#words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#puts combine_anagrams(words)