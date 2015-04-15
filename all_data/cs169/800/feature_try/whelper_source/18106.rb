def combine_anagrams(words)
  ret = {}
  words.each do |w|
    grp = w.downcase.split(//).sort.join
    ret.has_key?(grp) ? (ret[grp].insert(-1, w)) : (ret[grp] = [w])
  end
  ret.values
end

