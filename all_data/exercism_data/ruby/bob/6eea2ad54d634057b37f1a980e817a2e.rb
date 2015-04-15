class Bob

  def hey(message)
    dialogue = Dialogue.new(message)

    case
    when dialogue.silence?
      'Fine. Be that way.'
    when dialogue.question?
      'Sure.'
    when dialogue.shouting?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

end

class Dialogue

  def initialize(message)
    @message = message.to_s
  end

  def silence?
    @message.strip.empty?
  end

  def question?
    @message.end_with?('?')
  end

  def shouting?
    @message.upcase == @message
  end

end
