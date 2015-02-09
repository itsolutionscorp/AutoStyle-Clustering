def combine_anagrams(words)
  result = Array.new
  aux = Array.new
  words.each { |value| aux.push(value.downcase.sum.to_s) }
  aux.uniq.each do |valueSum|
    result.push(words.select { |v| (v.downcase.sum.to_s == valueSum) })
  end
  return result
end