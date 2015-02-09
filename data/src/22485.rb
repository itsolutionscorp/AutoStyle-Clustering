def combine_anagrams(words)
  res = Hash.new
  words.each do |word|
    key = word.downcase.split("").sort.join
    res[key] ||= []
    (res[key] << word)
  end
  return res.values
end