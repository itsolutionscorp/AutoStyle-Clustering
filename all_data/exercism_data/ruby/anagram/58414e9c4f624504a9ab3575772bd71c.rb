# warning: DCI ideas area:
#   data is gets its role(Word) before use
#
class Anagram
  def initialize(word) @word = word end

  def match variants
    variants.select { |variant|

      variant = Word.new variant
      word    = Word.new @word
      
      variant.chars_used == word.chars_used \
        and not variant == word
    }
  end
end

class Word
  def initialize(word) @word = word end

  def == other
    downcase == other.downcase
  end
  def chars_used
    downcase.chars.sort
  end

  # unsafe delegator, to eliminate use of attr_reader and (.word x 3 times)
  def method_missing(*a) @word.send *a end
end
