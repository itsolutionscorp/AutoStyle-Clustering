def combine_anagrams(list)
  h = Hash.new
  list.each do |word|
    key = word.downcase.split("").sort.join("")
    h.has_key?(key) ? ((h[key] << word)) : (h[key] = [word])
  end
  h.values
end

