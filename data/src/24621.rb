def combine_anagrams(words)
  ret = []  
  words.each do |word|
    if ret.empty?
      ret = [[word]]
    else  
      found = false
      ret.each_index do |x|
        if ret[x][0].downcase.chars.sort.join == word.downcase.chars.sort.join
          ret[x].push(word)
          found = true
          break
        end
      end
      if not found 
        ret.push([word])
      end
    end  
  end
  return ret
end