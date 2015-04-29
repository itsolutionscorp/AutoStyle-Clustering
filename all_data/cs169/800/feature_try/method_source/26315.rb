def combine_anagrams(words)
  groupz = Hash.new
  words.each do |w|
    sorted = w.downcase.chars.sort.join
    groupz.has_key?(sorted) ? ((groupz[sorted] << w)) : (groupz[sorted] = [w])
  end
  return groupz.values
end