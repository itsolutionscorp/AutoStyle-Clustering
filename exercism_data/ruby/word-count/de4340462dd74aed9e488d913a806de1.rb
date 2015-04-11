class Phrase
  def initialize(phrase)
    @phrase = phrase
    @counts = Hash.new(0)
    set_counts!
  end

  def word_count
    @counts
  end

  private

  def tokens
    @phrase.split(/[^\w]+/).map(&:downcase)
  end

  def set_counts!
    tokens.each do |token|
      @counts[token] += 1
    end
  end
end
