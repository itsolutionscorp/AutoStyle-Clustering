def combine_anagrams(words)
  alphabetic = Hash.new
  group = Array.new
  words.each { |w| alphabetic[w.downcase.chars.sort.join] ||= [] }
  words.each { |w| (alphabetic[w.downcase.chars.sort.join] << w) }
  alphabetic.each_value { |v| (group << v) }
  return group
end