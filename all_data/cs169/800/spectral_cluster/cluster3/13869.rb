def combine_anagrams(words)
  h = Hash.new
  words.each do |s|
    sorted = s.downcase.chars.sort.join
    unless h[sorted]
      h[sorted] = []
    end
    h[sorted] += [s]
  end
  result = []
  h.each_key do |k|
    result += [h[k]]
  end
  return result
end
