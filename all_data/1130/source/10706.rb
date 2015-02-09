
def combine_anagrams(words)
  map = {}
  words.each do |w|
  	puts w.downcase.chars.sort.join, w
  	(map[w.downcase.chars.sort.join] ||= []) << w
  end
  map.values
end
