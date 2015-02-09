def combine_anagrams(words)
  groups = words.collect {|x| [x]}
  groups.length.times do |group_index|
    group = groups[group_index]
    base_word = group[0].downcase
    words.each_with_index do |word, index|
      if index != group_index && word.downcase.chars.sort.join == base_word.chars.sort.join
        groups[group_index] << word
      end
    end
    groups[group_index].sort!
  end
  groups.uniq
end

p combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
p combine_anagrams(['cars', 'for', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
p combine_anagrams(['a', 'a', 'b', 'b', 'c', 'd'])
p combine_anagrams(['A', 'a'])