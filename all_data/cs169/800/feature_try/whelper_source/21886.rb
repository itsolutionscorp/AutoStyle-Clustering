def combine_anagrams(words)
  palindrome = {}
  words.each do |word|
    key = word.downcase.split("").sort.join
    if palindrome.has_key?(key) then
      (palindrome[key] << word)
    else
      palindrome[key] = [word]
    end
  end
  words = []
  palindrome.each_value { |value| (words << value) }
  return words
end

