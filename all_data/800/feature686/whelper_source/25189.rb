def combine_anagrams(words)
  h = Hash.new { |hash, key| hash[key] = [] }
  words.each { |x| (h[x.downcase.split("").sort!.join] << x) }
  return h.values
end

