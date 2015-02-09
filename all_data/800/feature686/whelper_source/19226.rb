def combine_anagrams(words)
  solution = Array.new
  if words.empty? then
    solution = []
  else
    if (words.length == 1) then
      solution = [words]
    else
      modify = words.map { |b| b.downcase.chars.to_a.sort.join }
      for i in (0..(modify.length - 1)) do
        (tempsol = [words[i]]
        for j in (0..(modify.length - 1)) do
          (tempsol << words[j]) if modify[i].eql?((modify[j] and (i != j)))
        end
        (solution << tempsol))
      end
      solution.uniq!
    end
  end
  return solution
end

