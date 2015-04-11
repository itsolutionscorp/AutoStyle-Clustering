class Bob
  def hey(msg)
    case kill_newlines(msg)
    when silence then  'Fine. Be that way!'
    when shouting then 'Woah, chill out!'
    when question then 'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence
    ->(msg){ msg.match(/^\s*$/) }
  end

  def shouting
    ->(msg){ msg == msg.upcase }
  end

  def question
    ->(msg){ msg.end_with? '?' }
  end

  def kill_newlines msg
    msg.gsub("\n", '')
  end
end
