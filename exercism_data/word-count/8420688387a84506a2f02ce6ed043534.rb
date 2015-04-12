class Phrase
  def initialize string
    @string = string
  end

  def word_count
    extract_words_from_string
    build_word_count_hash
  end

  private
  attr_reader :string
  attr_accessor :words

  def extract_words_from_string
    self.words = string.downcase
      .split(/[^0-9a-z]/i)
      .reject &:empty?
  end

  def build_word_count_hash
    Hash.new(0).tap do |hash|
      words.each { |word| hash[word] += 1 }
    end
  end
end
