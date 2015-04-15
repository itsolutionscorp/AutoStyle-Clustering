def combine_anagrams(words)
  h = Hash.new
  words.each do |word|
    name = word.downcase.split(//).sort.join
    h[name] ||= []
    (h[name] << word)
  end
  h.values
end