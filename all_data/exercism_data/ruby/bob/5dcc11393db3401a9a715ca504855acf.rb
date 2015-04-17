class Bob
  def hey(message)
    if empty?(message)
      'Fine. Be that way.'
    elsif question?(message)
      'Sure.'
    elsif shout?(message)
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

  private

  def empty?(message)
    message.nil? || message.empty?
  end

  def question?(message)
    message.end_with? '?'
  end

  def shout?(message)
    message !~ /[a-z]/
  end
end