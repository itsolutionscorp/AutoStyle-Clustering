class Bob

  def hey(statement)
    message = Message.new(statement)
    if message.silence?
      'Fine. Be that way!'
    elsif message.angry?
      'Woah, chill out!'
    elsif message.inquiry?
      'Sure.'
    else
      'Whatever.'
    end
  end
end


class Message

  def initialize(statement)
    @statement = statement.to_s.strip
  end

  def silence?
    statement.empty?
  end

  def angry?
    statement.upcase == statement
  end

  def inquiry?
    statement.end_with?('?')
  end

  attr_reader :statement; private :statement

end
