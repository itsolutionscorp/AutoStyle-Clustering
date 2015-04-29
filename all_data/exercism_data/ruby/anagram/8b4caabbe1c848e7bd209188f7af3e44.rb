require 'contracts'

class Anagram
  include Contracts

  Contract String => Any
  def initialize(word)
    @word = word
    @ordered_word = order(word)
  end

  Contract ArrayOf[String] => ArrayOf[String]
  def match(anagram_list)
    anagram_list.select {|w| anagram?(w)}
  end

  private

  Contract String => String
  def order(word)
    word.downcase.chars.sort.join
  end

  Contract String => Bool
  def anagram?(w)
    @ordered_word == order(w) && @word.downcase != w.downcase
  end
end
