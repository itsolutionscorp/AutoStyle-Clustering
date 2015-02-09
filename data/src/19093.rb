def combine_anagrams(words)
  hsh = {}
  aout = words.each do |wrd|
    akey = wrd.downcase.chars.sort.join
    hsh[akey].nil? ? (hsh[akey] = [wrd]) : ((hsh[akey] << wrd))
  end
  return hsh.values
end