def combine_anagrams(words)
  ana_hash = {}
  words.each do |x|
    ana_x = x.downcase.chars.sort.join
    if ana_hash.include? ana_x
      ana_hash[ana_x] = ana_hash[ana_x] + [x]
    else
      ana_hash[ana_x] = [x]
    end
  end
  
  return ana_hash.values
end
