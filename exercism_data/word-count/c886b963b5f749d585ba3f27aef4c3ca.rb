class Phrase
  def initialize(phrase)
    @words = phrase.gsub(/[\s\b:,]/, " ").gsub(/[^\w\s']/, "").split
  end

  def word_count
    Hash[@words.map do |word|
      [ word.downcase, count_word_in_phrase(word) ]
    end]
  end

  private

  def count_word_in_phrase(word)
    @words.count { |current_word| word.casecmp(current_word).zero? }
  end

end
