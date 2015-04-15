def combine_anagrams(words)
  groups = Hash.new
  words.each do | word |
    group_id = word.downcase.chars.sort.join
    group = groups[group_id]
    if (!group)
      group = Array.new
      groups[group_id] = group
    end

    group << word
  end

  return groups.values
end