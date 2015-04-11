class Phrase
  attr_reader :words

  def initialize(phrase)
    @words = parse(phrase)
  end

  def word_count
    words.reduce(Hash.new(0)) do |hsh, word|
      hsh[word] += 1
      hsh
    end
  end

  private

  def parse(phrase)
    all_words = phrase.gsub(/[^\w\,\'\s]/, "").split(/[\s\,]/)
    all_words.reject(&:empty?).map(&:downcase)
  end
end
