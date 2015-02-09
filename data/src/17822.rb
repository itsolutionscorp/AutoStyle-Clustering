def combine_anagrams(words)
  output = Array.new
  while (words.length > 0) do
    temp = Array.new
    temp[0] = words.slice!(0)
    temp2 = temp[0].downcase.split(//).sort
    words.each_index do |s|
      if (words[s].downcase.split(//).sort == temp2) then
        (temp << words[s])
        words[s] = nil
      end
    end
    (output << temp)
    words.compact!
  end
  return output
end