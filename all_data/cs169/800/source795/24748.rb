def combine_anagrams(words)
  hCheck = Hash.new()
  counter = 0
  output = Array.new()
  words.each do |w|
    if hCheck[w.downcase.sum] then
      output[hCheck[w.downcase.sum]].push(w)
    else
      hCheck[w.downcase.sum] = counter
      output.push(Array.new().push(w))
      counter += 1
    end
  end
  return output
end
