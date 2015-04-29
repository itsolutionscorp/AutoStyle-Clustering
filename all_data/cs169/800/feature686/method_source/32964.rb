def combine_anagrams(words)
  arrangedwords = Hash.new([])
  words.each do |w|
    key = w.downcase.chars.sort.join
    arrangedwords[key] = (arrangedwords[key] + [w])
  end
  return arrangedwords.values
end