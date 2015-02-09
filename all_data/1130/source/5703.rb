def combine_anagrams(words)
  hash = Hash.new
  words.each do |word|
    sortedword = word.downcase.chars.sort.join
    if hash[sortedword] == nil
      hash[sortedword] = [word]
    else
      hash[sortedword] = hash[sortedword] + [word]
    end
  end
  return hash.values
end

print combine_anagrams(['cars','racs','god','dog','agog'])
