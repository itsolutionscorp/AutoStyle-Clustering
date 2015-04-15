def combine_anagrams(words)
  result = Array.new
  while (not words.empty?) do
    out = Array.new
    words.each do |word|
      (out << word) if (words[0].downcase.chars.sort == word.downcase.chars.sort)
    end
    words = (words - out)
    (result << out)
  end
  return result
end

