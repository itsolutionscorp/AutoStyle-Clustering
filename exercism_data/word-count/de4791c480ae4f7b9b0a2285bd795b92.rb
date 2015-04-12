class Phrase
  attr_reader :word_count

  def initialize phrase
    self.word_count = Hash.new 0
    self.phrase = sanitize phrase
    calculate
  end

  private

  def sanitize phrase
    strip_punctuation(phrase).split.map &:downcase
  end

  def strip_punctuation phrase
    phrase.gsub /\W/, " "
  end

  def calculate
    phrase.each do |word|
      word_count[word] += 1
    end
  end

  attr_accessor :phrase
  attr_writer :word_count
end
