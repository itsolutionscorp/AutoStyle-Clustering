def combine_anagrams(words)
  h = Hash.new
  for index in 0...words.size
    strTemp = words[index].downcase.chars.sort { |a, b| a.casecmp(b) }.join
    if h.has_key?(strTemp)
      
      h[strTemp] = h.fetch(strTemp).concat([words[index]])
    else
    
      h[strTemp] = [words[index]]
    end
  end
    return h.values
end
