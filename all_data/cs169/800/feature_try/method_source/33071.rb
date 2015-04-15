def combine_anagrams(words)
  l = Array.new
  words.each do |z|
    s = Array.new
    sorted = z.chars.sort(&:casecmp).join
    words.each do |i|
      if (i.downcase.chars.sort(&:casecmp).join == sorted) and (not l.flatten.include?(i)) then
        (s << i)
      end
    end
    (l << s) if (not s.empty?)
  end
  return l.sort!
end