def combine_anagrams(words)
  h = Hash.new
  words.each do |word|
    base = word.downcase.chars.sort.join
    h.key?(base) ? (h[base] = (h[base] << word)) : (h[base] = [word])
  end
  ans = []
  h.each { |key, value| ans = (ans << value) }
  return ans
end

