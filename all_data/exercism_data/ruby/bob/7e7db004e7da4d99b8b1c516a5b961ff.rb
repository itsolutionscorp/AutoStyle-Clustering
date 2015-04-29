class Bob
  def hey(message)
    statment = Statement.new message
    case true
    when statment.screaming?
      'Woah, chill out!'
    when statment.question?
      'Sure.'
    when statment.nothing?
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
    @sentence[-1] == '?'
  end

  def nothing?
    clean_spaces.empty?
  end

  private

  def clean
    @sentence.tr('^A-Za-z', '')
  end

  def clean_spaces
    @sentence.gsub(/\s/, '')
  end
end
