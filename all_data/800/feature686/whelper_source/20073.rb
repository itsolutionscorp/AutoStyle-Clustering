def combine_anagrams(words)
  o = []
  words.each do |wi|
    t = []
    words.each do |wj|
      t.push(wj) if (wi.downcase.split(//).sort == wj.downcase.split(//).sort)
    end
    o.push(t)
  end
  return o.uniq
end

