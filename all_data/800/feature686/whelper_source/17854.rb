def combine_anagrams(words)
  output = []
  words.each do |word|
    if output.empty? then
      (output << [word])
    else
      i = 0
      found = false
      output.each do |out|
        target = out[0]
        if (target.to_s.downcase.split(//).sort.to_s.downcase == word.to_s.downcase.split(//).sort.to_s.downcase) then
          (output[i] << word)
          found = true
        end
        i = (i + 1)
      end
      (output << [word]) if (not found)
    end
  end
  output
end

