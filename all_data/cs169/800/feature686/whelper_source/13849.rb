def combine_anagrams(inp)
  grps = {}
  inp.each do |wrd|
    k = wrd.downcase.split(//).sort!
    grps.has_key?(k) ? ((grps[k] << wrd)) : (grps[k] = [wrd])
  end
  answer = grps.values
  return answer
end

