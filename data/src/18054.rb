def combine_anagrams(words)
  groups = {}
  words.each { |word|
    dist_word = word.downcase.split('').sort.join('')
    if groups.keys.select { |grp| grp == dist_word }.length == 0
      groups[dist_word] = [ word ]
#    elsif groups[dist_word].select { |w| w == word }.length == 0
    else
      groups[dist_word].push(word)
    end
  }
  return groups.values
end
