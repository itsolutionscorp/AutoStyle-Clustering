def combine_anagrams(words)
  hash = Hash.new([])
  words.each_with_index do |word, index|
    x = word.downcase.chars.sort.join
    puts(x)
    (hash[x].length > 0) ? ((hash[x] << index)) : (hash[x] = [index])
  end
  ans = []
  hash.each_value do |arr|
    xarr = []
    arr.each { |ind| (xarr << words[ind.to_i]) }
    (ans << xarr)
  end
  return ans
end