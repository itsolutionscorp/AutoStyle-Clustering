def combine_anagrams(words = [])
  output = Hash.new
  
  words.each do |word|
    sorted = word.downcase.chars.sort.join
    
    if output[sorted].nil?
      output[sorted] = [word]
    else 
      output[sorted] += word
    end
  end
return output.values
end

