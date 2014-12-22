
def combine_anagrams(words)
  res = {};
  
  words.each {
    |w|
    k = w.downcase.chars.sort.join;
    res[k] = res[k].to_a.push(w)
  }
  return res.values
end

