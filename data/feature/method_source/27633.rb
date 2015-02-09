def combine_anagrams(words)
  temp_hash = Hash.new { |h, k| h[k] = [] }
  combined = []
  words.each { |x| (temp_hash[x.downcase.chars.sort.join] << x) }
  temp_hash.each_value { |v| (combined << v) }
  combined
end