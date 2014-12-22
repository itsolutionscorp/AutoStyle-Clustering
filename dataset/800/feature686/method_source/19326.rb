def combine_anagrams(words)
  words.collect do |w|
    words.find_all { |w2| w.split(//).sort.eql?(w2.split(//).sort) }.sort.uniq
  end.uniq
end