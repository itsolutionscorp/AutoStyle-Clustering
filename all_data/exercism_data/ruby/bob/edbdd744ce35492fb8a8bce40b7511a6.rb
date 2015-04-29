class Message
  attr_reader :text

  def initialize(message)
    @text = message.strip
  end

  def question?
    text.end_with? "?"
  end

  def shouting?
    text == text.upcase && text[/[A-Z]/]
  end

  def blank?
    text.empty?
  end
end

class Person
  attr_reader :message

  def hey(text)
    @message = Message.new(text)
    respond
  end

  def respond
    return respond_to_nothing if message.blank?
    return respond_to_shouting if message.shouting?
    return respond_to_question if message.question?
    respond_to_statement
  end

  def respond_to_nothing
    'Fine. Be that way!'
  end

  def respond_to_shouting
    'Woah, chill out!'
  end

  def respond_to_question
    'Sure.'
  end

  def respond_to_statement
    'Whatever.'
  end
end

class Bob < Person
end
