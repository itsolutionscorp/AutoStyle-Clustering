class Bob
  RESPONSES = {
    :shout => 'Woah, chill out!',
    :question => 'Sure.',
    :silence => 'Fine. Be that way!',
    :default => 'Whatever.',
  }

  def hey(message)
    reply_to Interpretation.of(message)
  end

  def reply_to(interpretation)
    RESPONSES.fetch interpretation
  end
end

class Interpretation
  POSSIBILITIES = [
    :shout,
    :question,
    :silence,
  ]

  attr_reader :message

  def self.of(message)
    self.new(message).interpretation
  end

  def initialize(message)
    @message = message.to_s.strip
  end

  def interpretation
    POSSIBILITIES.detect { |possible_interpretation|
      send(:"#{possible_interpretation}?")
    } || :default
  end

  def shout?
    message.upcase == message unless silence?
  end

  def question?
    message.end_with? '?'
  end

  def silence?
    message.empty?
  end
end
