def combine_anagrams(words)
  groups = Hash.new
  output = Array.new
  words.each do |word|
    hash = word.downcase.chars.sort.join
    groups[hash] = Array.new if (groups[hash] == nil)
    (groups[hash] << word)
  end
  groups.each { |key, value| (output << value) }
  print("The grouping is: ")
  print(output)
  print("\n")
  return output
end

