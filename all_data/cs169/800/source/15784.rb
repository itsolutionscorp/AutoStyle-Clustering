def combine_anagrams(words)
  anagrams = {} 

  words.each do |w|
    key = w.downcase.scan(/[a-zA-Z]/).sort.join
    if anagrams.has_key? key
      anagrams[key] << w
    else
     anagrams[key] = [w]
    end
  end

  anagrams.values
end


