def combine_anagrams(words)
  result = []
  words.each do |word|
    sorted = word.downcase.chars.sort.join
    if (result.size > 0) then
      result.each do |r|
        if (r[0].downcase.chars.sort.join == sorted) then
          (r << word)
          break
        else
          (result << [word])
          break
        end
      end
    else
      result = [[word]]
    end
  end
  result
end

