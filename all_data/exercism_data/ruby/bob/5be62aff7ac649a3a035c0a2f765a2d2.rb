class Bob
  def hey(message)
    reply Phrase.new(message)
  end

  def reply(phrase)
    if phrase.shout? || phrase.forceful_question?
      'Woah, chill out!'
    elsif phrase.question?
      'Sure.'
    elsif phrase.silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Phrase
  attr_reader :message
  def initialize(message)
    message = '' if message.nil?
    @message = message
  end

  def shout?
    message.length >= 1 && message.upcase == message
  end

  def forceful_question?
    shout? && question?
  end

  def question?
    message.end_with? '?'
  end

  def silence?
    message.nil? || message.strip == ''
  end
end
