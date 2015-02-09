def combine_anagrams(words)
  h = Hash.new
  words.each do |word|
    sortedword = word.downcase.chars.sort.join
    temp = h[sortedword]
    if (temp != nil) then
      h[sortedword] = (temp + [word])
    else
      h[sortedword] = [word]
    end
  end
  return h.values.to_a
end