def combine_anagrams(words)

  groups = []

  words.each do |word|
    found= false

    if groups.length == 0
      groups = groups + [[word]]

    else
      groups.map! do |group|

        if group[0].downcase.chars.sort.join == word.downcase.chars.sort.join
          group = group + [word]
          found = true
        end
        group
      end

      if found == false; groups = groups + [[word]]; end

    end
  end
  return groups
end

