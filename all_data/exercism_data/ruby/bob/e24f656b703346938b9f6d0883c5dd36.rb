class Bob

  def initialize
  end

  def hey message
    if says_nothing? message
      'Fine. Be that way.'
    elsif yelling? message
      'Woah, chill out!'
    elsif question? message
      'Sure.'
    else
      'Whatever.'
    end
  end

  def says_nothing? message
    message.nil? or message == ''
  end

  def yelling? message
    message =~ /(?=^[^a-z]*$)(?=.*[A-Z]+.*)/
  end

  def question? message
    message.length > 0 and message[-1] == '?'
  end

end
