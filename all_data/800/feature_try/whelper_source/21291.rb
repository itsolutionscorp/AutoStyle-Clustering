def combine_anagrams(words)
  words.inject({}) do |memo, word|
    alfa_word = word.downcase.split("").sort.join
    memo[alfa_word] ||= []
    (memo[alfa_word] << word)
    memo
  end.values
end

