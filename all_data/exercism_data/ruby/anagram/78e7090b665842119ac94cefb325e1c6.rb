# Taking it too far ;-)
class Anagram
  def initialize(word)
    @word  = Word(word)
  end

  def match(list)
    list.select { |candidate| @word.anagram_of?(candidate) }
  end

  module Conversions
    module_function
    def Word(object)
      case object
      when Word then object
      else object.to_str.extend(Word)
      end
    end
  end
  include Conversions

  module Word
    def anagram_of?(other)
      !same_word_as?(other) && same_letters_as?(other)
    end

    protected
    def letters
      downcase.chars.sort
    end

    private
    def same_letters_as?(other)
      Conversions::Word(other).letters == letters
    end

    def same_word_as?(other)
      other.downcase == self.downcase
    end
  end
end
