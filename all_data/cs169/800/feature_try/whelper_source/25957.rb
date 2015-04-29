def combine_anagrams(words)
  out = {}
  words.each do |word|
    sorted = word.downcase.chars.sort.join
    out[sorted] = [] unless out.has_key?(sorted)
    out[sorted].push(word)
  end
  out.values
end

