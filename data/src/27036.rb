def combine_anagrams(words)
  output = Array.new
  words.each do |w|
    output = (output + [words.select do |w1|
      (w1.downcase.chars.sort.join == w.downcase.chars.sort.join)
    end])
  end
  output = output.uniq
  puts("Combining Anagrams")
  p(output)
end