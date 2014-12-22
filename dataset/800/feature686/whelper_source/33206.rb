def combine_anagrams(words = [])
  result = Hash.new
  words.each do |word|
    prepared = word.downcase.chars.sort.join
    if result[prepared].nil? then
      result[prepared] = [word]
    else
      result[prepared] += [word]
    end
  end
  return result.values
end

