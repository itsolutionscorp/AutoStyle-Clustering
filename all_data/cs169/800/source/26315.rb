def combine_anagrams(words)
  groupz = Hash.new
  words.each { |w|
    sorted = w.downcase.chars.sort.join
    if groupz.has_key?(sorted)
      groupz[sorted] << w
    else
      groupz[sorted] = [w]
    end
  }
  return groupz.values
end