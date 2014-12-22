def combine_anagrams(words)
  groups = []
  words.each do |word|
    sorted = sort_word(word)
    idx = groups.index { |g| (sort_word(g[0]) == sorted) }
    idx.nil? ? (groups.push([word])) : (groups[idx].push(word))
  end
  return groups
end

def sort_word(w)
  return w.downcase.split(/\s*/).sort.join
end

