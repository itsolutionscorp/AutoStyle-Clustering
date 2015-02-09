def combine_anagrams(words)
  grouped = Hash.new
  anagrams = Array.new
  words.each do |word|
    split = word.downcase.split(//)
    sorted_word = split.sort { |x, y| (x <=> y) }
    if grouped.has_key?(sorted_word) then
      (grouped[sorted_word] << word)
    else
      array = [word]
      grouped.store(sorted_word, array)
    end
  end
  grouped.each_value { |x| (anagrams << x) }
  return anagrams
end

