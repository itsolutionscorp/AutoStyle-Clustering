def combine_anagrams(words)
  hsh = {}
  words.each do |word|
    index = word.downcase.split(//).sort.join
    hsh[index] = [] if (not hsh[index])
    (hsh[index] << word)
  end
  result = []
  hsh.each { |index, group| (result << group) }
  result
end