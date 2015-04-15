class Bob
  def hey(message)
    statment = Statement.new message
    if statment.screaming?
      'Woah, chill out!'
    elsif statment.question?
      'Sure.'
    elsif statment.nothing?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Statement
  def initialize(sentence)
    @sentence = sentence.to_s
  end

  def screaming?
    message = clean
    !message.empty? && message.upcase == clean
  end

  def question?
    @sentence.end_with?('?')
  end

  def nothing?
    @sentence.strip.empty?
  end

  private

  def clean
    @sentence.tr('^A-Za-z', '')
  end
end
