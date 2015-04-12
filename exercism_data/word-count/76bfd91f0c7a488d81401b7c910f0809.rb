class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    Hash[words.group_by(&identity).map { |k, v| [k, v.count] }]
  end

  private

  def words
    text.downcase.gsub(/[^a-z0-9 ]+/, ' ').split
  end

  def identity
    ->(x) { x }
  end

  attr_reader :text
end
