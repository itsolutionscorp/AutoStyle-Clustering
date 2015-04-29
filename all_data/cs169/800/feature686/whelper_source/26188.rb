def combine_anagrams(words)
  h = Hash.new
  words.length.times do |i|
    v = 0
    w = words[i].split("").sort!.join("")
    w.length.times { |j| v = (v + w.clone.slice!(j).downcase.ord) }
    if h.key?(v) then
      (h[v] << words[i])
    else
      h[v] = Array.new
      (h[v] << words[i])
    end
  end
  return h.values
end

