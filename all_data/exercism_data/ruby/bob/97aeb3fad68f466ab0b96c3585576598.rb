class Bob
  def hey(what)
    message = Message.new(what)
    if message.completely_empty? then 'Fine. Be that way!'
    elsif message.upcase? then 'Woah, chill out!'
    elsif message.question? then 'Sure.'
    else "Whatever."
    end
  end
end

class Message < Struct.new(:message)

  def upcase?
    message == message.upcase
  end

  def question?
    message.end_with? '?'
  end

  def completely_empty?
    message.strip.empty?
  end
end
