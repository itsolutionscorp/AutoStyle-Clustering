def combine_anagrams(words)
  g = {}
  words.each do |w|
    sample = w.downcase.chars.sort { |a, b| a.casecmp(b) }.join
    g.has_key?(sample) ? ((g[sample] << w)) : (g[sample] = [w])
  end
  gr = []
  g.each do |k, v|
    subgr = []
    v.each { |w| (subgr << w) }
    (gr << subgr)
  end
  return gr
end

