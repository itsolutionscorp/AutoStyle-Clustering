
def combine_anagrams(words)
  results=Hash.new()
  words.each do |word|
    key = word.downcase.split("").sort.join
    if(results.has_key?(key))
      results[key]=results[key]+[word];
    else
      results[key]=[word]
    end
    
  end
  return results.values

end

