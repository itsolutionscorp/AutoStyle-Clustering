def combine_anagrams(words)
  rtn = []
  words.each do |w|
    added = false
    rtn.each do |a|
      if (w.downcase.chars.sort.join == a[0].downcase.chars.sort.join) then
        (a << w)
        added = true
      end
    end
    (rtn << [w]) if (not added)
  end
  return rtn
end