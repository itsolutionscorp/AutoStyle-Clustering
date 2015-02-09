def combine_anagrams(words)
  results = []
  ar_iter = 0
  number = words.size
  while (number != 0) do
    results[ar_iter] = Array.new
    word = words.first.to_s.downcase.chars.sort.join
    n = 0
    words.size.times { |number| n = (n + 1) if (words[number] == words.first) }
    n.times { (results[ar_iter] << words.first) }
    words = (words - words.first.to_s.split)
    number = (number - n)
    number2 = (words.size - 1)
    while (number2 >= 0) do
      if (words[number2].to_s.downcase.chars.sort.join == word) then
        (results[ar_iter] << words[number2])
        words = (words - words[number2].to_s.split)
        number = (number - 1)
      end
      number2 = (number2 - 1)
    end
    ar_iter = (ar_iter + 1)
  end
  return results
end

