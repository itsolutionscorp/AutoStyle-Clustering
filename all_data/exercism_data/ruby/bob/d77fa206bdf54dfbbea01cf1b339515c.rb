class Bob
  def hey(msg)
    case msg
    when msg.strip == ''
      'Fine. Be that way!'
    when is_yelling_at_you(msg) # yelling at you
      'Woah, chill out!'
    when (msg.ends_with? == '?')
      'Sure.'
    else
      'Whatever.'
    end
  end

  def is_yelling_at_you?(msg)
    /[A-Za-z]/.match(msg) && msg.upcase == msg
end
