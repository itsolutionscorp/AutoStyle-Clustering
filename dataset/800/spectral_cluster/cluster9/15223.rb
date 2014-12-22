def combine_anagrams(words)
  hsh = {}    # hash
  words.each do
    |word|
    key = word.downcase.scan(/\w/).sort
    if hsh.has_key?(key)
      hsh[key] = hsh[key] << word
    else 
      hsh.merge!({key => [word]})
    end
  end
  anagrams = []
  hsh.each_key do |key| anagrams << hsh[key] end
  return anagrams
end