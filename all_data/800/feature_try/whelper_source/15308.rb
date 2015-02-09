def classify
  h = Hash.new { |h, k| h[k] = [] }
  self.each do |x|
    k = yield(x)
    h[k] = (h[k] << x)
  end
  return h
end

def combine_anagrams(words)
  return words.classify { |x| x.downcase.chars.sort.join }.each_value.collect do |x|
    x.to_a
  end
end

