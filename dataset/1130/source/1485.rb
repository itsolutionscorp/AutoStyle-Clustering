def combine_anagrams(words)

  grouped = []

  words.each do |word|
     # concatenate all of the anagrams with the grouped array
     grouped << words.select do |other_word|
       (other_word.downcase.chars.sort.join == word.downcase.chars.sort.join) &&
       (not grouped.flatten.include? other_word )    # make sure not to include existing words
     end
  end

  # leave out empty elements
  grouped.select { |element| not element.empty? }
end