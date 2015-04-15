def combine_anagrams(words)
  groups = []
  words.each do |word|
    groups.each do |group|
      a = group[0].upcase.split(//).sort.join
      b = word.upcase.split(//).sort.join
      if (a == b) then
        (group << word)
        word = ""
        break
      end
    end
    (groups << [word]) if (word != "")
  end
  return groups
end

