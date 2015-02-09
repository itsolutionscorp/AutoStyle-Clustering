def combine_anagrams(words)
  output = []
  sorted_input = words.map { |word| word.downcase.chars.sort.join }
  while (words.size != 0) do
    aim = [words.shift]
    sorted_input.shift
    sorted_input.each_with_index do |word, index|
      if (word == aim[0].downcase.chars.sort.join) then
        (aim << words.delete_at(index))
        sorted_input.delete_at(index)
      end
    end
    (output << aim)
  end
  output
end

