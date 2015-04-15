def combine_anagrams(words)
  sorted = Hash.new
  words.each{|w| 
    angrm = w.downcase.chars.sort.join
    if !sorted.has_key?(angrm)
      sorted[angrm] = Array.new
    end
    sorted[angrm] << w
  }
  return sorted.values
end
