def combine_anagrams(words)
  result = Array.new
  words.each do |word|
    isMatch = false
    result.each do |group|
      if is_match(group, word) then
        (group << word)
        isMatch = true
        break
      end
    end
    (result << [word]) if (not isMatch)
  end
  return result
end