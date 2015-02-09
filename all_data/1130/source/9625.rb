
def combine_anagrams(words)
  h = Hash.new { |h,k| h[k] = [] }
  words.each { |w| h[w.downcase.chars.sort.join] << w}
  list = []
  h.each { |k,v| list << v}
  return list
end
