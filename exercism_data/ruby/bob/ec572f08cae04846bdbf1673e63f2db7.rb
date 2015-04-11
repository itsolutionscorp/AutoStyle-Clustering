class Bob
  def hey message
    return 'Fine. Be that way!' if blank? message
    return 'Woah, chill out!' if yelling? message
    return 'Sure.' if question? message
    return 'Whatever.' 
  end

  private
  def blank? message
    message.strip.empty?
  end

  def yelling? message
    message == message.upcase && message != message.downcase
  end

  def question? message
    message.end_with? '?'
  end
end
