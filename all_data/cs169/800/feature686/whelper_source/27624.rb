def combine_anagrams(words)
  o = Hash.new
  words.each do |word|
    w = word.downcase.split(/\s*/).sort.join
    o[w] = [] if o[w].nil?
    (o[w] << word)
  end
  o.values
end

