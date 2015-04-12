class Phrase
  attr_reader :sentence

  def initialize(sentence)
    @sentence = sentence
    sanitize!
  end

  def word_count
    words.each_with_object({}) do |word, result|
      result[word] = result[word].to_i + 1
    end
  end

  private
    def words
      sentence.split.map(&:downcase)
    end

    def sanitize!
      sentence.gsub!(/[^a-z0-9\']/i, ' ')
    end

end
