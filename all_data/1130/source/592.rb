def combine_anagrams(words)
  res = []
  words.each_index do |ind|
    if !res.flatten.include?(words[ind])
      part = []
      words[ind..words.length].each do |word|
        if !res.flatten.include?(word)
          if words[ind].downcase.split(//).sort == word.downcase.split(//).sort
            part << word
          end
        end
      end
      res[res.length] = part
    end
  end
  return res
end
