def combine_anagrams(wds)
  anagrams = {}
  wds.each do |w|
    grp = w.downcase.split(//).sort.join
    if anagrams.has_key?(grp) then
      anagrams[grp].insert(-1, w)
    else
      anagrams[grp] = [w]
    end
  end
  anagrams.values
end