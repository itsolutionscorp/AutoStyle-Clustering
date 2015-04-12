class Phrase
  attr_reader :content

  def initialize(content)
    @content = content
  end

  def word_count
    words.inject(Hash.new(0)) { |acc, current| acc[current] += 1; acc }
  end

  private

  def words
    content.scan(/(\w+)+/).flatten.map(&:downcase)
  end
end
