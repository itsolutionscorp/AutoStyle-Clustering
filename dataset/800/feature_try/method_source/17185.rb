def combine_anagrams(input)
  result = Hash.new
  input.each do |word|
    ana = word.downcase.scan(/./).sort
    list = result[ana]
    list = Array.new if (not list)
    result[ana] = list.push(word)
  end
  return result.values
end