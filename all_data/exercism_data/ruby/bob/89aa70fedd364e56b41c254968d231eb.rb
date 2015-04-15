class Bob
  def hey(message)
    message = InterrogatableString.new(message)

    if message.yelling?
      'Woah, chill out!'
    elsif message.questioning?
      'Sure.'
    elsif message.silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class InterrogatableString < String
  def initialize(message)
    @message = message
  end

  def yelling?
    # exclude all non-alphabetic characters, then check if uppercase is the same as the message
    filtered_message = @message.scan(/[a-zA-Z]*/).join
    
    # for all-numeric phrases, filtering all but the alphabet will leave "" == ""
    return false if filtered_message.empty?

    filtered_message == filtered_message.upcase
  end

  def questioning?
    @message.end_with? '?'
  end

  def silence?
    @message.strip.empty?
  end
end
