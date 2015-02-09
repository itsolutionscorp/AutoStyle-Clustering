def combine_anagrams(words)
  sorted = Hash.new
  final = Hash.new { |h, k| h[k] = [] }
  words.each do |word|
    sortedword = word.downcase.chars.sort.join
    sorted[word] = sortedword
  end
  sorted.each { |k, v| final[v].push(k) }
  return final.values
end