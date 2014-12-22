

# @param [Array] words
def upgraded_combine_anagrams(words)
  tab = Array.new
  #  words.each_index do |t|
  #    tab << words.fetch(t).downcase.chars.sort(&:casecmp).join
  #  end
  #  tab.uniq!
  l = Array.new
  words.each do |z|
    s = Array.new
    sorted = z.chars.sort(&:casecmp).join
    words.each {|i| s << i if i.downcase.chars.sort(&:casecmp).join.include?(sorted) and not l.flatten.include?(i)}
    l << s if not s.empty?
  end

  return l
end

# @param [Array] words
def combine_anagrams(words)
   l = Array.new
  words.each do |z|
    s = Array.new
    sorted = z.chars.sort(&:casecmp).join
    words.each {|i| s << i if i.downcase.chars.sort(&:casecmp).join == (sorted) and not l.flatten.include?(i)}
    l << s if not s.empty?
  end

   return l.sort!
end
