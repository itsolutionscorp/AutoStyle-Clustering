def is_anagram(x, y)
  return (x.chars.sort.join.downcase == y.chars.sort.join.downcase)
end

def combine_anagrams(words)
  groups = []
  words.each_index do |x|
    g = Array.new
    g.push(words[x])
    words.each_index do |y|
      g.push(words[y]) if is_anagram(words[x], words[y]) unless (x == y)
    end
    groups.push(g) unless groups.flatten.index(words[x])
  end
  return groups
end

