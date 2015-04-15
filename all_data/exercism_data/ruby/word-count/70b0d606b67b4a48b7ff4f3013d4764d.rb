class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    sentence.split(/[:\s,.!@\$%\^&]/)
            .delete_if(&:empty?)
            .map(&:downcase)
            .each_with_object(Hash.new(0)) do
              |word, counts| counts[word] += 1
            end
  end

  private
  attr_reader :sentence
end
