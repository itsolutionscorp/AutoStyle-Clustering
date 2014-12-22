def combine_anagrams(words)
  groups = Hash.new
  words.each do |w|
    chars = []
    w.each_char { |c| (chars << c) }
    key = chars.sort.join
    groups[key] ? ((groups[key] << w)) : (groups[key] = [w])
  end
  groups.collect { |k, v| v }
end

