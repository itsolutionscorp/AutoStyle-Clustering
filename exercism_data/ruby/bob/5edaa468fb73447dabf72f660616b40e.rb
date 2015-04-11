class Bob
  def hey message
    if empty? message
      'Fine. Be that way!'
    elsif yelling? message
      'Woah, chill out!'
    elsif inquisitive? message
      'Sure.'
    else
      'Whatever.'
    end
  end

  def inquisitive? message
    message =~ /\?\z/
  end

  def yelling? message
    message.upcase == message && message =~ /[A-Z]+/
  end

  def empty? message
    message.strip.empty?
  end
end
