def combine_anagrams(words)
  sortedWords = words.collect { |x| [x, x.chars.sort { |a, b| a.casecmp(b) }.join.downcase] }
  hashMap = Hash.new
  sortedWords.each do |x|
    if hashMap.has_key?(x[1]) then
      hashMap[x[1]] += [x[0]]
    else
      hashMap[x[1]] = [x[0]]
    end
  end
  return hashMap.values
end

