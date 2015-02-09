class WrongNumberOfWordsError < StandardError; end

def combine_anagrams(words)
  raise WrongNumberOfWordsError unless words.length > 0  
  out = words.group_by { |word| word.chars.sort }.values
  out
end