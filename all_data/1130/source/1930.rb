def combine_anagrams(words)
  results = Hash.new([]);
  words.each {|word| 
    rearranged = word.downcase.split(//).sort.join
    results[rearranged] += [word]
  }
  return results.values;
end
