def combine_anagrams(words)
  combined = Array.new
  anagrams = Hash.new
  words.sort.each do |w|
    a = w.downcase.chars.sort.join
    (not anagrams[a]) ? (anagrams[a] = [w]) : (anagrams[a] += [w])
  end
  anagrams.each { |a, b| combined = (combined + [b.sort]) }
  return combined
end