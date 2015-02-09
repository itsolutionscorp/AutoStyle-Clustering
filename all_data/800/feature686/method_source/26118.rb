def combine_anagrams(words)
  result = []
  words.each do |w|
    added = false
    if result.empty? then
      (result << [w])
      added = true
    else
      result.each do |r|
        unless r.empty? then
          if (r[0].chars.sort(&:casecmp).join.casecmp(w.chars.sort(&:casecmp).join) == 0) then
            (r << w)
            added = true
          end
        end
      end
      (result << [w]) unless added
    end
  end
  return result
end