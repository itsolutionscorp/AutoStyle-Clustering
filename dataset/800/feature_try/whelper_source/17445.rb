def combine_anagrams(words)
  hash = Hash.new(0)
  words.each do |x|
    key = x.downcase.chars.sort.join
    if (not hash.has_key?(key)) then
      hash[key] = [x]
    else
      hash[key] = (hash[key] + [x])
    end
  end
  return hash.values
end

