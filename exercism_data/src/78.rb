class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    text
      .split(/[^\w']+/)
      .group_by(&:downcase)
      .map { |w, g| [w, g.size] }
      .to_h
  end

  private

  attr_reader :text
end
