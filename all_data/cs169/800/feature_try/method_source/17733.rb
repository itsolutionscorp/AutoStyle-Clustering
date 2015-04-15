def combine_anagrams(words)
  ana_groups = Hash.new([])
  words.each do |word|
    norm_word = word.downcase.chars.sort.join
    ana_groups[norm_word] = (ana_groups[norm_word] + [word])
  end
  ana_groups.map { |k, v| v }
end