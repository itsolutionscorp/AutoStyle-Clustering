def combine_anagrams(words)
  groups = Hash.new {|k,v| k[v] = []}
  words.each do |w|
    groups[w.downcase.split("").sort.join] << w
  end
  return groups.values
end

