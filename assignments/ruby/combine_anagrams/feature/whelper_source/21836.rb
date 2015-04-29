def combine_anagrams(words)
  sortedword = Array.new
  words.each { |word| (sortedword << word.downcase.chars.sort.join) }
  ar_last = Array.new
  sortedword.each do |w|
    if sortedword.include?(w) then
      m = 0
      ar_temp = Array.new
      while (sortedword.size != m) do
        (ar_temp << words.at(m)) if (w == sortedword[m])
        m = (m + 1)
      end
      (ar_last << ar_temp)
    end
  end
  ar_last.uniq
end

