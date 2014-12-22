def combine_anagrams(words)
  sortedhash = {}
  sortkeys = []
  anagrams = []
  words.each {|e| sorted = e.downcase.each_char.sort
  	              if sortedhash.has_key?(sorted)
  	              	sortedhash[sorted].push(e)
  	              else
  	                 sortedhash[sorted] = [e]; sortkeys.push(sorted); end }
  sortkeys.each {|j| anagrams.push(sortedhash.values_at(j))}
  return anagrams.flatten(1)
end