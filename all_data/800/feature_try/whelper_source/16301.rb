def combine_anagrams(words)
  sortWords = Array.new
  agHash = Hash.new
  agWords = Array.new
  words.each do |w|
    x = w.downcase
    sortWords.push(x.chars.sort { |a, b| a.casecmp(b) }.join)
  end
  sortWords.each_index do |index|
    if agHash.has_key?(sortWords[index]) then
      agHash[sortWords[index]].push(words[index])
    else
      agHash[sortWords[index]] = [words[index]]
    end
  end
  agHash.each_value { |v| agWords.push(v) }
  return agWords
end

