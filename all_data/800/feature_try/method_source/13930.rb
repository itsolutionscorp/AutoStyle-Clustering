def combine_anagrams(words)
  groups = []
  words.each do |word|
    found = false
    groups.each do |group|
      if (normalize(group[0]) == normalize(word)) then
        found = true
        group.push(word)
      end
    end
    groups.push([word]) unless found
  end
  groups
end