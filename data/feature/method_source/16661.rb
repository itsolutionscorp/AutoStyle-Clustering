def combine_anagrams(words)
  groups = Hash.new
  words.each do |word|
    key = word.downcase.chars.sort.join
    groups[key] = (groups[key].to_a + [word])
  end
  result = []
  groups.each { |key, value| result = (result + [value]) }
  result
end