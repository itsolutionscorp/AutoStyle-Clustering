def combine_anagrams(words)
  h={}
  h.default=[]
  anagrams=[]
  words.each { |a| w=a.downcase;sw=w.split('').sort().join();h[sw]=h[sw]+[a] }
  h.map { |k,v| anagrams.push(v)}
  anagrams
end
