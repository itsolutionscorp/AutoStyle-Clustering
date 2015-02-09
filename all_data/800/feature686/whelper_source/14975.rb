def combine_anagrams(words)
  bases = {}
  words.each do |wrd|
    base = wrd.downcase.chars.sort.join
    bases.has_key?(base) ? (bases[base] += [wrd]) : (bases[base] = [wrd])
  end
  bases.values
end

