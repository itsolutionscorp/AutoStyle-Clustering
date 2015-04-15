class Anagram
  def initialize(start_word)
    @start_word = start_word
  end
  attr_reader :start_word

  def match(list)
    list.select do |list_words|
      unless start_word.downcase == list_words.downcase
        list_words.downcase.chars.sort == start_word.downcase.chars.sort
      end
    end
  end

end
