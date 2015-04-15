class Phrase
  attr_reader :text

  def initialize text
    @text = text.downcase
  end

  def word_count
    @word_count ||= Hash.new(0).tap do |word_hash|
      words.each { |word| word_hash[word] += 1 }
    end
  end

  private

  def words
    @words ||= text.scan(/\w+\'\w+|\w+/)
  end
end
