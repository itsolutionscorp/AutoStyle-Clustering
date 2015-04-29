class CharList
  attr_reader :char_list, :down_word

  def initialize(word)
    @down_word = word.downcase
    @char_list = @down_word.chars.sort
  end

  def matches?(otherCharList)
    @down_word != otherCharList.down_word &&
      @char_list == otherCharList.char_list
  end

  def to_s
    @char_list.to_s
  end
end

class Anagram
  attr_reader :word
  
  def initialize(word)
    @word = word
  end

  def match(word_list)
    word_list.select { |candidate|
      char_list.matches? CharList.new candidate
    }
  end

  private

  def char_list
    @char_list ||= CharList.new word
  end
end
