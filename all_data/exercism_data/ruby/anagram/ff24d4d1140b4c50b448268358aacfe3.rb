class Anagram

  attr_reader :word

def initialize(word)
  @word = word
end


def match(set_of_words)
  set_of_words.select do |match_word|
    word.downcase != match_word.downcase && 
      word.downcase.split("").sort == 
      match_word.downcase.split("").sort
  end
end

end
