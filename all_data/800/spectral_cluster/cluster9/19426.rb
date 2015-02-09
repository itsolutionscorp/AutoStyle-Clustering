def combine_anagrams(words)
  # Group by samples
  g = {}
  words.each do |w|
    sample = w.downcase.chars.sort { |a, b| a.casecmp(b) } .join
    if g.has_key? sample
      g[sample] << w
    else
      g[sample] = [w]
    end
  end

  gr = []
  g.each do |k, v|
    subgr = []
    v.each do |w|
      subgr << w
    end
    gr << subgr
  end
  return gr
end
