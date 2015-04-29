# Cleary not ideal:
# - enumerating punctuation is not really efficient
# - word_count is not easily readable/understandable
class Phrase
  def initialize(phrase)
    @phrase = phrase.to_s
    normalize
  end

  def word_count
    counted_word = Hash.new

    @phrase.split(" ").each {|word|
        if counted_word.key?(word)
          counted_word[word] += 1
        else
          counted_word[word] = 1
        end
    }

    counted_word
  end

  private

  def normalize
    @phrase.downcase!
    remove_punctuation
    @phrase
  end

  def remove_punctuation
    @phrase.tr! '!?#@\.,&$%^:', ''
  end
end
