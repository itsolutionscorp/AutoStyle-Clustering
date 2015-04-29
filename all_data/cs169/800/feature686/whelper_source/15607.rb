def combine_anagrams(words)
  sorted = Hash.new
  words.each do |w|
    angrm = w.downcase.chars.sort.join
    sorted[angrm] = Array.new if (not sorted.has_key?(angrm))
    (sorted[angrm] << w)
  end
  return sorted.values
end

