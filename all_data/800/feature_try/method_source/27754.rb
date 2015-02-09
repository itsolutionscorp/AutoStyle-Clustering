def combine_anagrams(words)
  hsh = {}
  words.each do |word|
    key = word.chars.sort_by(&:downcase).join
    hsh[key] = [] if (not hsh.has_key?(key))
    (hsh[key] << word)
  end
  result = []
  hsh.keys.each { |k| (result << hsh[k]) }
  return result
end