class Bob

  MESSAGES =  {
    :whatever      => "Whatever.",
    :chillout     => "Woah, chill out!",
    :be_that_way  => "Fine. Be that way!",
    :sure         => "Sure.",
  }

  def hey(message)
    if message == nil || message.strip == ""
      MESSAGES[:be_that_way]
    elsif (/[a-z]/ =~ message) == nil && (/[A-Z]/ =~ message)
      MESSAGES[:chillout]
    elsif message[-1,1] == '?'
      MESSAGES[:sure]
    else
      MESSAGES[:whatever]
    end
  end
end
