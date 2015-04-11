class Bob
  def hey(remark)
    message = Message.new(remark)

    if message.silence?
      'Fine. Be that way!'
    elsif message.shouting?
      'Whoa, chill out!'
    elsif message.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  class Message < String
    def silence?
      strip.empty?
    end

    def shouting?
      upcase == self && self != downcase
    end

    def question?
      strip.end_with? '?'
    end
  end
end
