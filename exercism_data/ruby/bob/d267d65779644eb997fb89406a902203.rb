class Bob
  def hey message
    if message.strip.empty?
      'Fine. Be that way!'
    elsif yelling? message
      'Woah, chill out!'
    elsif asking? message
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def asking? message
    message.end_with? '?'
  end

  def yelling? message
    message == message.upcase
  end
end
