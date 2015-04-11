class Bob
  def hey message
    message ||= ""
    return 'Fine. Be that way!' if blank? message
    return 'Woah, chill out!' if yelling? message
    return 'Sure.' if question? message
    "Whatever."
  end

  private

  def blank? message
    message.strip == ''
  end

  def yelling? message
    message.upcase == message
  end

  def question? message
    message.end_with? '?'
  end
end
