def combine_anagrams(words)
  a = []
  words.each { |m| (a << m.downcase.chars.sort.join) }
  a.uniq!
  puts(a)
  res = []
  a.each do |w|
    grp = []
    words.each { |k| (grp << k) if (k.downcase.chars.sort.join == w) }
    (res << grp)
  end
  return res
end