def combine_anagrams(words)
  output = Array.new
  n = words.length
  for i in 0..n-1
    if output.length == 0
      output.concat([[words[i]]])
    else
      added = false
      k = output.length
      for j in 0..k-1
        if output[j][0].chars.sort == words[i].chars.sort
          output[j].concat([words[i]])
          added = true
        end
      end
      if not added
        output.concat([[words[i]]])
      end
    end
  end
  return output
end