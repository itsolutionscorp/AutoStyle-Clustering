def combine_anagrams(words)
  newlist = words.map { |each_word| each_word.split(//).sort.join.downcase }
  h = {}
  newlist.each_index do |index|
    h[newlist[index]] = [] unless h.has_key?(newlist[index])
    (h[newlist[index]] << words[index])
  end
  anagramlist = []
  h.each { |key, val| (anagramlist << h[key]) }
  return anagramlist
end