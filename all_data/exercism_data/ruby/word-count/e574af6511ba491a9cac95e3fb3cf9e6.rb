# A little better than the last one.
# Not sure if word_count is the most
# expressive/clean possible yet !
class Phrase
  def initialize(phrase)
    @phrase = phrase.to_s
    normalize_phrase
    @word_list = get_word_list
  end

  def word_count
    counted_word = Hash.new

    get_uniq_word.each {|word|
      counted_word[word] = @word_list.count(word)
    }

    counted_word
  end

  private

  def normalize_phrase
    @phrase.downcase!
  end

  def get_word_list
    @phrase.scan(/\w+/)
  end

  def get_uniq_word
    @word_list.uniq
  end
end
