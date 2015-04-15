def combine_anagrams(words)
  combos = {}
  words.each do |word|
    key = word.downcase.split(//).sort.to_s
    if combos[key] == nil
      combos[key] = [word]
    else
      combos[key] << word
    end
  end
  
  retval = []
  combos.each do |k, v|
    retval << v
  end
  retval
end
