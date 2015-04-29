def combine_anagrams(words)
  output = []

  words.each do |word|
    if output.any?

      a = 0 # flag
      output.each do |list|
        comparison = list[0].is_a?(String) ? list[0] : list[0][0]

        if (word.downcase.chars.sort.join == comparison.downcase.chars.sort.join)
          idx = output.index(list)
          output[idx] << word
          a = 1
          break
        end
      end

      output << [word] if a == 0
    elsif output.empty?
      output << [word]
    else
      puts "INSIDE ELSE CONDITION"
    end
  end

  output
end
