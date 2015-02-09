def combine_anagrams(words)
  output = Array.new
  words.each do |word|
    foundit = false
    if (output.length > 0) then
      output.each do |group|
        if (group[0].downcase.split("").sort == word.downcase.split("").sort) then
          (group << word)
          foundit = true
        end
      end
      (output << [word]) if (foundit == false)
    else
      (output << [word])
    end
  end
  output
end