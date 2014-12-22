def combine_anagrams(words)
  aHash={}
  words.each do |w| 
    if aHash.include? w.downcase.chars.to_a.sort then
      aHash[w.downcase.chars.to_a.sort] << w
    else
      aHash[w.downcase.chars.to_a.sort]=[w]
    end
  end
  aHash.values
end
