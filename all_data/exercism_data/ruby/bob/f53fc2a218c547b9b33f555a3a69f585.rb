module MessageInterpretation
  def empty?
    ->(message) { message.nil? || message.strip == '' }
  end

  def shout?
    ->(message) { message.upcase == message }
  end

  def question?
    ->(message) { message[-1] == '?' }
  end
end

class Bob
  include MessageInterpretation

  def hey(message)
    case message
    when empty?    then 'Fine. Be that way!'
    when shout?    then 'Woah, chill out!'
    when question? then 'Sure.'
    else                'Whatever.'
    end
  end
end
