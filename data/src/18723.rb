def combine_anagrams(words)
  groups = []
  words.each do |word|
    found = false
    if (groups.length == 0) then
      groups = (groups + [[word]])
    else
      groups.map! do |group|
        if (group[0].downcase.chars.sort.join == word.downcase.chars.sort.join) then
          group = (group + [word])
          found = true
        end
        group
      end
      groups = (groups + [[word]]) if (found == false)
    end
  end
  return groups
end