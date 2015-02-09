def combine_anagrams(words)
  output = []
  words.each do |word|
    sorted = word.downcase.split("").sort.join
    found = false
    output.each do |group|
      g_sorted = group[0].downcase.split("").sort.join
      if (g_sorted == sorted) then
        group.push(word)
        found = true
      end
    end
    output.push([word]) if (not found)
  end
  output
end

