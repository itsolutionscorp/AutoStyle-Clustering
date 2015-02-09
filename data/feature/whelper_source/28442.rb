def combine_anagrams(words)
  sortedhash = {}
  sortkeys = []
  anagrams = []
  words.each do |e|
    sorted = e.downcase.each_char.sort
    if sortedhash.has_key?(sorted) then
      sortedhash[sorted].push(e)
    else
      sortedhash[sorted] = [e]
      sortkeys.push(sorted)
    end
  end
  sortkeys.each { |j| anagrams.push(sortedhash.values_at(j)) }
  return anagrams.flatten(1)
end

