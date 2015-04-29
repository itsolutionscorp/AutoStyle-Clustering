def combine_anagrams(words)
  #words = words.map{|d| d.downcase}
  similar = words.map{|e| [e.downcase.split(//).sort.inject(""){|v,d| v.concat(d)}, e]}
  #puts "similar ", similar.inspect
  sorted = similar.sort
  #puts "sorted ", sorted.inspect
  result = []
  current = nil
  previous = nil
  sorted.each{|d|
    if (previous != d[0])
      previous = d[0]
      if (current  != nil)
        result.push(current)
      end
      current = []
    end
    current.push(d[1])
  }
  result.push(current)
  return result
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect
puts combine_anagrams(['cars', 'ba', 's', 'racs', 'b','b', 'A', 'a']).inspect
puts combine_anagrams([]).inspect
