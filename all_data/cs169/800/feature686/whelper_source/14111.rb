def combine_anagrams(words)
  result = []
  words.each do |word|
    match = false
    result.each do |rs|
      if (rs[0].downcase.split("").sort == word.downcase.split("").sort) then
        (rs << word)
        match = true
      end
    end
    arr = []
    (arr << word)
    (result << arr) unless match
  end
  return result
end

