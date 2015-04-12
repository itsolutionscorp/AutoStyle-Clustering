class Phrase

  def initialize(phrase)
    @phrase = normalize(phrase)
  end

  def word_count
    build_word_count_object
  end

  private

  def input_phrase
    @phrase
  end

  def normalize(phrase)
    phrase.gsub(/[!&@$%^&:]/, '')
          .gsub(/,/, ' ')
          .strip
          .downcase
          .split
  end

  def build_word_count_object
    input_phrase.inject({}) do |wco, word| 
      wco.merge({word => @phrase.count(word)}) 
    end
  end
 
end
