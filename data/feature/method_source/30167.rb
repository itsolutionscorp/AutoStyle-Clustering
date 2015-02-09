def combine_anagrams(words)
  groups = Hash.new
  words.each do |w|
    k = w.downcase.chars.sort.join
    (groups[k] == nil) ? (groups[k] = [w]) : ((groups[k] << w))
  end
  result = []
  groups.each_pair { |k, v| (result << v) }
  return result
end