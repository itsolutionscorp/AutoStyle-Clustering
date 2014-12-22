def combine_anagrams(words)
  words_already_selected = ""
  result = []
  words.each do |w|
    next if words_already_selected.include?(w)
    group = words.select do |x|
      x.downcase.chars.sort.join.eql?(w.downcase.chars.sort.join)
    end
    words_already_selected = (words_already_selected + group.join("|"))
    (result << group)
  end
  return result
end