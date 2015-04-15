def combine_anagrams(words)
  h = {}
  c = words.map do |w|
    if (h[w.downcase.chars.sort.join] == nil) then
      h[w.downcase.chars.sort.join] = [w]
    else
      h[w.downcase.chars.sort.join] += [w]
    end
  end
  a = []
  h.each_key { |key| (a << h[key]) }
  return a
end