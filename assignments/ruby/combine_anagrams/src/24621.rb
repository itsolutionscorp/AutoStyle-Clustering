def combine_anagrams(words)
  ret = []
  words.each do |word|
    if ret.empty? then
      ret = [[word]]
    else
      found = false
      ret.each_index do |x|
        if (ret[x][0].downcase.chars.sort.join == word.downcase.chars.sort.join) then
          ret[x].push(word)
          found = true
          break
        end
      end
      ret.push([word]) if (not found)
    end
  end
  return ret
end