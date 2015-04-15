class Phrase
  LETTERS_OR_NUMBERS_REGEXP = /[a-z0-9]+/

  attr_accessor :message
  def initialize(message)
    @message = message
  end

  def word_count
    words.inject(Hash.new(0)) do |result, elem|
      result[elem] += 1
      result
    end
  end

  private

  def words
    message.downcase.scan(LETTERS_OR_NUMBERS_REGEXP)
  end
end
