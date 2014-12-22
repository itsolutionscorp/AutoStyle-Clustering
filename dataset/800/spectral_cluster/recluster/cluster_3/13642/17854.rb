def combine_anagrams(words)
  output = []
  words.each do |word|
    if output.empty?
      # put first word in an array in output array
      output << [word]
    else
      i = 0
      found = false
      output.each do |out|
        target = out[0] 
        if target.to_s.downcase.split(//).sort.to_s.downcase == word.to_s.downcase.split(//).sort.to_s.downcase
          output[i] << word
          found = true
        end
        i = i + 1
      end
      if not found ; output << [word] ; end
    end
  end
  output    
end

# p combine_anagrams(["HeLLo", "hello"])
# p combine_anagrams(['A','a'])
# p combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
