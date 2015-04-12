class Phrase
  def initialize(phrase)
    @phrase = phrase
    @words = phrase.downcase.split /[^a-z0-9']/
  end

  attr_reader :phrase, :words

  def word_count
    @word_count ||= words.each_with_object(Hash.new(0)) do |word, word_count|
      word_count[word] += 1 unless word.empty?
    end
  end
end
