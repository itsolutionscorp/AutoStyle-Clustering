def combine_anagrams(pt)
  s = {}
  pt.each do |t|
    key = t.split("").sort.join
    s[key] ||= []
    (s[key] << t)
  end
  s.values
end

