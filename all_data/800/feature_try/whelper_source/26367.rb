def combine_anagrams(words)
  wordsB = words.dup
  combine_array = Array.new
  while (words.length > 0) do
    wordA = words[0]
    enagums = Array.new
    enagums.push(wordA)
    puts(wordA.chars.sort { |a, b| a.casecmp(b) }.join)
    words.delete(wordA)
    wordsB.delete(wordA)
    index = 0
    while (index < wordsB.length) do
      wordB = wordsB[index]
      if (wordA.chars.sort { |a, b| a.casecmp(b) }.join == wordB.chars.sort { |a, b| a.casecmp(b) }.join) then
        enagums.push(wordB)
        wordsB.delete(wordB)
        words.delete(wordB)
      else
        index = (index + 1)
      end
    end
    combine_array.push(enagums)
  end
  puts(combine_array.to_s)
end

