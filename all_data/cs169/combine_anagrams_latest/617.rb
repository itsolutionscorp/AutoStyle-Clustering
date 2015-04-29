def combine_anagrams(words) 
  d = {}
  for word in words
    id = word.downcase.chars.sort
    if d.include?(id)
      d[id].push word
    else
      d[id] = [ word ]
    end
  end 
  return d.values
end
