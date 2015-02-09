def combine_anagrams(input)
  result = Array.new(1)
  output = Array.new(2)
  input.each do |word|
    tempWord = word.downcase.chars.sort.join
    result.concat([tempWord]) if (result.index(tempWord) == nil)
    i = result.index(tempWord)
    (output[i] == nil) ? (output[i] = [word]) : ((output[i] << word))
  end
  (output - [nil])
end