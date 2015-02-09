def combine_anagrams(words)
  output = Array.new
  n = words.length
  for i in (0..(n - 1)) do
    if (output.length == 0) then
      output.concat([[words[i]]])
    else
      added = false
      k = output.length
      for j in (0..(k - 1)) do
        if (output[j][0].chars.sort == words[i].chars.sort) then
          output[j].concat([words[i]])
          added = true
        end
      end
      output.concat([[words[i]]]) if (not added)
    end
  end
  return output
end

