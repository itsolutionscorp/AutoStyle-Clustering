def combine_anagrams(words)
  anagrams = Hash.new { |h, k| h[k] = Hash.new(0) }
  print("=== words ===\n")
  words.each do |w|
    print("#{w}\n--")
    print("#{w.downcase.chars.sort.join}\n")
    anagrams[w.downcase.chars.sort.join][w] += 1
  end
  print("=== anagrams ===\n")
  anagrams.each { |x| print("#{x}\n") }
  print("=== combined ===\n")
  combined = []
  anagrams.each { |k, v| combined.push(v.keys) }
  combined
end