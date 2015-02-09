def combine_anagrams(words)
  hash = {}
  result = []
  words.each do |x|
    if hash.has_key?(x.downcase.scan(/\w/).sort) then
      hash.store(x.downcase.scan(/\w/).sort, hash.fetch(x.downcase.scan(/\w/).sort).push(x))
    else
      hash.store(x.downcase.scan(/\w/).sort, [x])
    end
  end
  hash.each_value { |value| result.push(value) }
  return result
end