class Phrase
  NOT_LETTERS_OR_NUMBERS_REGEXP = /[^a-z0-9]/

  attr_accessor :message
  def initialize(message)
    @message = message
  end

  def word_count
    words = normalized_message.split(' ')
    words.each_with_object(Hash.new(0)) { |elem, result|
      result[elem] += 1
    }
  end

  def normalized_message
    message.downcase.gsub(NOT_LETTERS_OR_NUMBERS_REGEXP, ' ').squeeze(' ')
  end
end
