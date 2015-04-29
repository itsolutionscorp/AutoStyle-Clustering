class Teenager

  def hey(input='')
    interpret(input || '')
  end

  def interpret(message)
    case
    when message.empty?;            'Fine. Be that way!'
    when message == message.upcase; 'Woah, chill out!'
    when message.end_with?('?');    'Sure.'
    else                            'Whatever.'
    end
  end

end

Bob = Class.new(Teenager)
