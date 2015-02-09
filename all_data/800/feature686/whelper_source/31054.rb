def combine_anagrams(words)
  temp_hash = Hash.new
  words.each do |word|
    unless temp_hash.key?(word.downcase.chars.sort.join) then
      temp_hash[word.downcase.chars.sort.join] = Array.new
    end
    (temp_hash[word.downcase.chars.sort.join] << word)
  end
  result = Array.new
  temp_hash.each { |key, value| (result << value) }
  return result
end

