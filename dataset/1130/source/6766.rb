def combine_anagrams(words)
  words2 = Hash.new
  words.each do |w|
    k = w.downcase.scan(/./).sort
    if words2.key? k
      arr = words2[k]
      arr[arr.length] = w
    else
      arr = [w]
      words2[k] = arr
    end
  end
  return words2.values
end
