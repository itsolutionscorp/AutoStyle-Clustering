def combine_anagrams(words)
  r = {}
  words.each do |word|
    key = word.downcase.chars.sort.join
    r[key] = [] if (not r.has_key?(key))
    r[key].push(word)
  end
  output = []
  r.each { |k, v| output.push(v) }
  return output
end

