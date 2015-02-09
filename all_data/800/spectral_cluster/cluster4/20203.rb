def combine_anagrams(words)
  result = Array.new
  while !words.empty? do
    out = Array.new
    words.each do |word| 
      if words[0].downcase.chars.sort == word.downcase.chars.sort
        out << word
      end
    end
    words -= out
    result << out
  end
  return result
end

