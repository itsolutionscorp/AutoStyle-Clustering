def combine_anagrams(words)
  new = {}
  words.each do |x|
    y = x.downcase.split(//).sort.join
    new.has_key?(y) ? (new[y].concat([x])) : (new[y] = [x])
  end
  return new.values
end