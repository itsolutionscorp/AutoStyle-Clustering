def combine_anagrams(words)
  anagramHash = Hash.new
  words.each do |word|
    wd = word.downcase.chars.sort.join
    if (anagramHash[wd] == nil) then
      anagramHash[wd] = [word]
    else
      anagramHash[wd].concat([word])
    end
  end
  result = Array.new
  anagramHash.each { |key, val| result = result.concat([val]) }
  return result
end