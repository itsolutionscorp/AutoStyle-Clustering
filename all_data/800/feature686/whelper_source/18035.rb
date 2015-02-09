def combine_anagrams(words)
  result = Hash.new
  words.each do |word|
    if result.has_key?(word.downcase.chars.sort.join) then
      result[word.downcase.chars.sort.join] += [word]
    else
      result[word.downcase.chars.sort.join] = [word]
    end
  end
  return result.values
end

