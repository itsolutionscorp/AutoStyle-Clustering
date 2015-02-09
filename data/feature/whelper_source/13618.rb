def combine_anagrams(words)
  sorted = words.map { |w| w.downcase.split("").sort }
  known = []
  result = []
  n = words.length
  words.each_index do |i|
    if (known.index(i) == nil) then
      palindroms = [words[i]]
      known.push(i)
      for j in ((i + 1)..n) do
        if (known.index(j) == nil) and (sorted[i] == sorted[j]) then
          palindroms.push(words[j])
          known.push(j)
        end
      end
      result.push(palindroms)
    end
  end
  return result
end

