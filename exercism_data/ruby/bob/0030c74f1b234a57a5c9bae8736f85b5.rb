class Bob
  attr_reader :parser

  def initialize
    @parser = Parser.new
  end

  def hey(message)
    send("respond_to_#{parser.determine_type(message)}")
  end

  private

  def respond_to_question
    'Sure.'
  end

  def respond_to_yell
    'Woah, chill out!'
  end

  def respond_to_empty
    'Fine. Be that way!'
  end

  def respond_to_default
    'Whatever.'
  end
end

class Parser
  def determine_type(message)
    return :empty if message.is_empty?
    return :yell if message.is_a_yell?
    return :question if message.is_a_question?
    return :default
  end
end

class String
  def is_a_question?
    return self[-1] == '?' ? true : false
  end

  def is_a_yell?
    return self == self.upcase ? true : false
  end

  def is_empty?
    return self.strip.empty?
  end
end
