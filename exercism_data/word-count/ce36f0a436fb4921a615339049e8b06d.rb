class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= begin
      counts = Hash.new(0)
      words.each { |word| counts[word.downcase] += 1 }
      counts
    end
  end

  private

  def words
    @phrase.scan(/\w+/)
  end
end
