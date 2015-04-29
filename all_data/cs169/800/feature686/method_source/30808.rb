def combine_anagrams(words)
  count = 0
  result = []
  while (words.length != 0) do
    current = words[0].downcase.chars.sort { |a, b| a.casecmp(b) }.join
    group = []
    (group << words[0])
    words.delete_at(0)
    words.each_with_index do |key, index|
      compareWord = key.downcase.chars.sort { |a, b| a.casecmp(b) }.join
      if (current == compareWord) then
        (group << words[index])
        words.delete_at(index)
      end
    end
    (result << group)
    count = (count + 1)
  end
  return result
end