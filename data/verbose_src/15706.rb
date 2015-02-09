def combine_anagrams(words)
  h = Hash.new
  words.each {|word|
    key = word.downcase.split(//).uniq.sort.join
    if ! h.has_key? key
      h[key] = Array.new
    end
    h[key].push(word)
  }
  return h.values.to_a
end
