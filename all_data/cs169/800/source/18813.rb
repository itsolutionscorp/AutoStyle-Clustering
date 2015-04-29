def combine_anagrams(words)
  if words.class != Array
    return words
  end

  hash = {}
  words.each do |word| 
    wkey = word.downcase.split(//).sort.join()
    #puts "wkey: #{wkey}"
    if hash.key?(wkey) 
      #puts "hash.key? #{wkey} = #{hash.key?(wkey).to_s}"
      if not hash[wkey].include?(word) 
        hash[wkey] << word
      end
    else
      hash[wkey] = [word]
    end
  end
  hash.values
end
