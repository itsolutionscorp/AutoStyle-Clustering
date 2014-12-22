def combine_anagrams(words)
  wsorted = []
  words.each { |w| (wsorted << w.downcase.split(//).sort.join) }
  gpd = []
  wsorted.each do |w|
    grouped = []
    i = 0
    wsorted.each do |c|
      (grouped << words[i]) if (w == c)
      i = (i + 1)
    end
    (gpd << grouped)
  end
  return gpd.uniq
end