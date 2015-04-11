class Bob

  def hey(message)
    if silent?(message)
      return 'Fine. Be that way.'
    elsif shouts?(message)
      return "Woah, chill out!"
    elsif asks?(message)
      return "Sure."
    else
      return "Whatever."
    end
  end


  private

  def silent?(message)
    message.empty?
  end

  def shouts?(message)
    message == message.upcase
  end

  def asks?(message)
    message.end_with?('?')
  end
end
