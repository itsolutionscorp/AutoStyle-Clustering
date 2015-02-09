def combine_anagrams(words)
  result = words.inject({}) do |result, x|
    key = x.split("").sort.to_s.downcase.to_sym
    result[key] = [x] unless result.keys.include?(key)
    (result[key] << x) if result.keys.include?(key)
    result
  end.values
  result.collect! { |y| y[(1..-1)] }
end

