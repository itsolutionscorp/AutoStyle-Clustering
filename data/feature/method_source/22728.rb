def combine_anagrams(words)
  hash = {}
  anagrams = []
  words.each do |w|
    sorted = w.downcase.each_char.sort.join
    if hash.has_key?(sorted) then
      hash[sorted].push(w)
    else
      hash[sorted] = Array.new.push(w)
    end
  end
  hash.each_value { |v| anagrams.push(v) }
  return anagrams
end