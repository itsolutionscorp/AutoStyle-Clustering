# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  groups = Hash.new
  words.each do |word|
    (groups[word.downcase.chars.sort.join] ||= []) << word
  end
  groups.flatten.values_at(* groups.flatten.each_index.select {|i| i.odd?})
end
