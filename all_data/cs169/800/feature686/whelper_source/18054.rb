def combine_anagrams(words)
  groups = {}
  words.each do |word|
    dist_word = word.downcase.split("").sort.join("")
    if (groups.keys.select { |grp| (grp == dist_word) }.length == 0) then
      groups[dist_word] = [word]
    else
      groups[dist_word].push(word)
    end
  end
  return groups.values
end

