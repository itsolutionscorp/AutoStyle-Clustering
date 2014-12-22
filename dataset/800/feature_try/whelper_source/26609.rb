def combine_anagrams(words)
  h = Hash.new { |hash, key| hash[key] = Array.new }
  enu = words.each
  enu.each_with_object(h) do |i, obj|
    ana = i.downcase.chars.sort.join
    (h[ana] << i)
  end
  return h.values
end

