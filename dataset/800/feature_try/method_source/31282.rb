def combine_anagrams(words)
  groups = []
  added = false
  words.each do |word|
    added = false
    groups.each do |group|
      if are_anagrams?(group[0], word) then
        group.push(word)
        added = true
        break
      end
    end
    groups.push([word]) if (not added)
  end
  return groups
end