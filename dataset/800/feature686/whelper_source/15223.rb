def combine_anagrams(words)
  hsh = {}
  words.each do |word|
    key = word.downcase.scan(/\w/).sort
    if hsh.has_key?(key) then
      hsh[key] = (hsh[key] << word)
    else
      hsh.merge!(key => ([word]))
    end
  end
  anagrams = []
  hsh.each_key { |key| (anagrams << hsh[key]) }
  return anagrams
end

