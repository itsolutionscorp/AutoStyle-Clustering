
def combine_anagrams(words)
  groups_hash = words.group_by {|w| w.downcase.chars.to_a.sort }
  groups_array = []
  groups_hash.each {|k, group| groups_array << group}
  groups_array
end
