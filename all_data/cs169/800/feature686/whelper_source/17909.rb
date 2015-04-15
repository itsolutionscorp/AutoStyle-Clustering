def combine_anagrams(words)
  cp = 0
  result = Array.new
  hash = Hash.new([])
  words.each do |word|
    cp = (cp + 1)
    (cp..words.count).each do |i|
      if (not hash[word.to_s.downcase.chars.sort.join].include?(word)) then
        hash[word.to_s.downcase.chars.sort.join] += [word]
      end
    end
  end
  hash.each { |key, value| result.push(value) }
  return result
end

