def combine_anagrams(words)
  h = Hash.new
  words.each do |word|
    if h[word.downcase.split("").sort] then
      h[word.downcase.split("").sort].push(word)
    else
      h[word.downcase.split("").sort] = [word]
    end
  end
  l = []
  h.each { |key, value| l.push(value) }
  return l
end

