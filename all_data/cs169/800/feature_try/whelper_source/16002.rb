def combine_anagrams(words)
  grp = {}
  words.each do |ww|
    norm = ww.downcase.split("").sort.join
    grp[norm] ? ((grp[norm] << ww)) : (grp[norm] = [ww])
  end
  grp.values
end

