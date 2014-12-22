def combine_anagrams(words)
  temp = words
  p = Array.new
  h = Hash.new
  temp.each { |word| h = makeHash(h, word) }
  h.values.each { |ary| p.push(ary) }
  return p
end

def makeHash(h, word)
  seq = word.downcase.scan(/\w/).sort.join
  if h.has_key?(seq) then
    h[seq] = h[seq].push(word)
  else
    h[seq] = Array.new
    h[seq] = h[seq].push(word)
  end
  return h
end

