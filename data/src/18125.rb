def combine_anagrams(words)
  word_cat = {}
  words.each do |w|
    s = w.downcase.split(//).sort
    word_cat[s] ? (word_cat[s].push(w)) : (word_cat[s] = [w])
  end
  result = []
  word_cat.each { |cat, wrds| result.push(wrds) }
  return result
end