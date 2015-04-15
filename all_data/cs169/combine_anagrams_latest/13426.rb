def combine_anagrams(words) 
  groups = {}

  words.each do |word|
      group = groups[word.downcase.chars.sort.join]

      if !group
        group = []
      end
      group << word
      groups[word.downcase.chars.sort.join] = group
  end

  out = []

  groups.each_pair do |key, group|
    out << group
  end

  return out
end