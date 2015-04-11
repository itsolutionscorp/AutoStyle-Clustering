class Phrase

  def initialize(phrase)
    @words = format(phrase)
  end

  def word_count
    build_word_count_object
  end

  private

  def words
    @words
  end

  def format(phrase)
    word_list = remove_non_word_chars(phrase).split
    normalize(word_list)
  end

  def remove_non_word_chars(phrase)
    phrase.gsub(/\W/, ' ')
  end

  def normalize(word_list)
    word_list.map{ | word | word.strip.downcase }
  end

  def build_word_count_object
    words.inject({}) do |wco, word| 
      wco.merge({word => words.count(word)}) 
    end
  end
 
end
