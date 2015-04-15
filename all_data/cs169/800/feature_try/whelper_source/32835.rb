def combine_anagrams(words)
  groups = Hash.new { default = [] }
  words.each do |word|
    key = word.downcase.chars.sort.join
    groups[key] = groups[key].push(word)
  end
  groups.values
end

