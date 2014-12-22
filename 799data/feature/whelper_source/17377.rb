def combine_anagrams(words)
  h = Hash.new(0)
  words.each do |w|
    key = w.downcase.chars.sort.join
    h[key] = [] if (h[key].class != Array)
    (h[key] << w)
  end
  ret = []
  h.each { |k, v| (ret << v) }
  return ret
end

