def combine_anagrams(words)
  return words if (words.size == 0)
  hash = Hash.new("Sorted")
  words.each { |x| hash[x] = x.downcase.unpack("c*").sort.pack("c*") }
  a = []
  a = hash.values.uniq
  retArray = Array.new
  for i in (0..(a.size - 1)) do
    (h2 = hash.select { |k, v| (v == a.at(i)) }
    retArray.push(h2.keys))
  end
  return retArray
end