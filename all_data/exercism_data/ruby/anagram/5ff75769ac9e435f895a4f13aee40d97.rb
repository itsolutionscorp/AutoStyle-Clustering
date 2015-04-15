class Anagram

  def initialize(word_to_match)
    @word_to_match = word_to_match
  end

  def match(list_of_words)
    result = []

    list_of_words.each do |word|
      if split_up(word) == split_up(@word_to_match) && word.downcase != @word_to_match.downcase
        result << word
      end
    end
    result
  end

  def split_up(word)
    word.downcase.scan(/\w/).sort
  end
end
