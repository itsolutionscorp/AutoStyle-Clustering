def combine_anagrams(words)
  sort = Array.new
  anagrams = Array.new	
  words.each_index {|x| sort[x] = words[x].downcase.chars.sort.join }
  ind = 0
  to_delete = Array.new
  while sort.length > 0
    to_delete.clear
    anagrams[ind] = Array.new
    anagrams[ind].push(words[0])
    for i in 1..sort.length
      if sort[i] == sort[0]
        anagrams[ind].push(words[i])
      end
    end
	  anagrams[ind].each {|x| words.delete(x)}
    ind = ind + 1
    str = sort[0]
    sort.delete(str)
  end
  return anagrams
end

combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] )