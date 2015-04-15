class Bob
  def hey(msg)
    case msg
    when shouting then 'Woah, chill out!'
    when question then 'Sure.'
    when silent then 'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def shouting
    lambda { |msg| msg == msg.upcase && !msg.strip.empty? }
  end

  def question
    lambda { |msg| msg[-1, 1] == "?" }
  end

  def silent
    lambda { |msg| msg.strip.empty? }
  end
end
