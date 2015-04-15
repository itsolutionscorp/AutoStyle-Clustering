module Teenager
  module Vocabulary
    CANNED_RESPONSE = 'Whatever.'

    REACTIONS = {
      yelling: 'Whoa, chill out!',
      question: 'Sure.',
      silence: 'Fine. Be that way!'
    }

    def respond_to(phrase)
      REACTIONS[phrase.type] or CANNED_RESPONSE
    end
  end
end

class Phrase
  TYPES = [:yelling, :question, :silence]

  def initialize(raw_phrase)
    @phrase = raw_phrase.strip
  end

  def question?
    phrase.end_with?('?')
  end

  def yelling?
    words? && phrase.upcase == phrase
  end

  def silence?
    phrase.empty?
  end

  def type
    TYPES.find {|type| self.public_send("#{type}?") }
  end

  private

  attr_reader :phrase

  def words?
    phrase.match(/[a-zA-Z]+/)
  end
end

class Bob
  include Teenager::Vocabulary

  def hey(provocation)
    phrase = Phrase.new(provocation)
    respond_to(phrase)
  end
end
