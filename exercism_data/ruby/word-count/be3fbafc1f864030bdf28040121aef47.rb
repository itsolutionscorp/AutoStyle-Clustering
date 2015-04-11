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
      downcased_words.each_with_object(Hash.new(0)) do |word, word_count|
        word_count[word] += 1
      end
    end

    def downcased_words
      sentence.downcase.scan(/['\w]+/)
    end
end
