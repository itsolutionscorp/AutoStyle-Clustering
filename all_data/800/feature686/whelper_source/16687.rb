def combine_anagrams(words)
  groups = Hash.new { |hash, key| groups[key] = Array.new }
  words.each do |word|
    w = word.downcase
    stamp = w.chars.sort.join
    (groups[stamp] << word)
  end
  return groups.values
end

