def combine_anagrams(words)
  def anagrams?(string1, string2)
    return (string1.downcase.split(//).sort == string2.downcase.split(//).sort)
  end
  groups = []
  until (words.length == 0) do
    (word = words.pop
    added_flag = false
    groups.each do |group|
      if anagrams?(group[0], word) then
        (group << word)
        added_flag = true
      end
    end
    (groups << [word]) if (not added_flag))
  end
  return groups
end