def combine_anagrams(words)
  map = Hash.new
  g = Array.new
  index = 0
  words.each do |w|
    if map[w.downcase.split("").sort.join].nil? then
      map[w.downcase.split("").sort.join] = index
      g[map[w.downcase.split("").sort.join]] = Array(w)
      index = (index + 1)
    else
      (g[map[w.downcase.split("").sort.join]] << w)
    end
  end
  g
end