def combine_anagrams(words)
  input = words
  hash = {}
  sorted = Array.new
  input.each { |i| (sorted << i.downcase.chars.sort.join) }
  unique = sorted.uniq
  unique.each do |s|
    hash[s] = Array.new
    sorted.each_index { |x| (hash[s] << words[x]) if (sorted[x] == s) }
  end
  output = Array.new
  hash.each_value { |value| (output << value) }
  pp(output)
end

