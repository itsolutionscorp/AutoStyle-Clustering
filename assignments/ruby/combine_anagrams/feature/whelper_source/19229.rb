def combine_anagrams(words)
  anagrams = []
  words.each do |word|
    grouped = false
    if anagrams.empty? then
      anagrams.push([word])
      grouped = true
    else
      anagrams.each do |group|
        if (group[0].downcase.split(//).sort == word.downcase.split(//).sort) then
          group.push(word)
          grouped = true
        end
      end
    end
    anagrams.push([word]) unless grouped
  end
  return anagrams
end

