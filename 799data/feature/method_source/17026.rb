def combine_anagrams(xs)
  return nil if (not xs.is_a?(Array))
  out = Hash.new
  xs.each do |x|
    if x.is_a?(String) and (x =~ /[^A-Za-z]/ == nil) then
      a = []
      x.downcase.scan(/[a-z]/) { |c| a = (a + [c]) }
      key = a.sort.join
      out[key] = (out.fetch(key, []) + [x])
    end
  end
  return out.values
end