def combine_anagrams(words)
  return [] if words == nil
  
  res = Array.new
  words.each do |elt|
    sub = res.assoc(elt.downcase.chars.sort.to_s)
    if sub == nil 
      res << [elt.downcase.chars.sort.to_s, words[words.index(elt)]]
    else
      sub << words[words.index(elt)]
    end
    res
  end
  res.each do |words| 
    words.delete_at(0)
  end
  res
end
