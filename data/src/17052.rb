def combine_anagrams(words)
  group = {}
  words.each do |s|
    key = s.downcase.chars.sort
    (group[key] == nil) ? (group[key] = [s]) : (group[key] = (group[key] << s))
  end
  arrayed = []
  group.each_value { |v| (arrayed << v) }
  arrayed
end