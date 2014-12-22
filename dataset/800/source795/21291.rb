def combine_anagrams(words) # <YOUR CODE HERE>
  words.inject({}){|memo, word|
    alfa_word = word.downcase.split("").sort.join
    memo[alfa_word] ||= []
    memo[alfa_word] << word
    memo
  }.values
end