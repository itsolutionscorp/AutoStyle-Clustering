def combine_anagrams(words)
  h = Hash.new
  words.each do |s|
    sorted = s.downcase.chars.sort.join
    h[sorted] = [] unless h[sorted]
    h[sorted] += [s]
  end
  result = []
  h.each_key { |k| result = (result + [h[k]]) }
  return result
end

