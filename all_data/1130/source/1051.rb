def combine_anagrams(words)
  groups = Hash.new(0)
  words.each do |word|
    if groups.has_key? word.downcase.chars.sort.join
      groups[word.downcase.chars.sort.join] << word
    else
      groups[word.downcase.chars.sort.join] = [word]
    end
  end
  groups.values
end