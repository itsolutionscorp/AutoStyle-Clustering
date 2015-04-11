class Phrase
  attr_reader :sentence

  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    @word_count ||= build_word_count
  end

  private

    def build_word_count
      downcased_words.inject(Hash.new(0)) do |word_count, word|
        word_count[word] += 1
        word_count
      end
    end

    def downcased_words
      words.map(&:downcase)
    end

    def words
      sentence.scan(/['\w]+/)
    end
end
