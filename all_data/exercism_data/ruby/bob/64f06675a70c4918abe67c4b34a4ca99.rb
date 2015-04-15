class Bob
  def hey(message)
    phrase = Phrase.new(message)
    if phrase.is_a_shout
      'Woah, chill out!'
    elsif phrase.is_a_question
      'Sure.'
    elsif phrase.is_silent
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

end

class Phrase
  attr_reader :message

  def initialize input
    @message = input
  end

  def is_a_question(message)
    message.end_with?('?')
  end

  def is_a_shout(message)
    message == message.upcase
  end

  def is_silent(message)
    message.nil? || message.empty?
  end
end
