def combine_anagrams(words)
  groups = {}

  words.each do |word|
    canonical = word.downcase.split("").sort.join
    if !groups.has_key? canonical then groups[canonical] = [] end
    groups[canonical].push word
  end

  groups.values
end
