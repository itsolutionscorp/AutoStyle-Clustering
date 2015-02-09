def combine_anagrams(words)
  groups = Hash.new([])
  words.each do |word|
    sortedLetters = word.downcase.chars.sort.join
    groups[sortedLetters] += [word]
    puts(groups[sortedLetters])
  end
  groups.values
end

