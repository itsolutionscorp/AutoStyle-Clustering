class Responder
  def initialize(value)
    @value = value.strip
  end

  def silent?
    @value.empty?
  end  

  def shouted?
    @value.upcase == @value
  end

  def question?
    @value.end_with?('?')
  end
end

class Bob
  def hey(query)
    message = Responder.new(query)
    case 
    when message.silent? then 'Fine. Be that way!'
    when message.shouted? then 'Woah, chill out!'
    when message.question? then 'Sure.'
    else 'Whatever.'
    end
  end
end
