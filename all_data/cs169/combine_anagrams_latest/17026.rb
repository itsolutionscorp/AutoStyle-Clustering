def combine_anagrams(xs)
  if !xs.is_a?(Array) then return nil; end

  out = Hash.new
  xs.each() { |x|
    if x.is_a?(String) and (x =~ /[^A-Za-z]/) == nil then
      a = []
      x.downcase().scan(/[a-z]/) { |c| a += [c] }
      key = a.sort().join()
      out[key] = out.fetch(key, []) + [x]
    end
  }

  return out.values()
  end
