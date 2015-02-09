def combine_anagrams(words)
  sortedwords = Array.new
  n = words.length
  for i in (0..(n - 1)) do
    (x = words[i].downcase.split(//).sort.join
    sortedwords.push(x) unless sortedwords.include?(x))
  end
  finalwords = Array.new
  m = sortedwords.length
  for j in (0..(m - 1)) do
    finalwords.push(words.select { |y| (y.downcase.split(//).sort.join == sortedwords[j]) })
  end
  return finalwords
end

