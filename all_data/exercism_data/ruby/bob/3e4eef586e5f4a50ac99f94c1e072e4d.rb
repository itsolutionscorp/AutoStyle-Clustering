class Bob

  attr_reader :message

  def hey(msg)
    @message = msg
    case
    when silence? then "Fine. Be that way."
    when yelling? then "Woah, chill out!"
    when asking? then "Sure."
    else "Whatever."
    end
  end

  private

  def asking?
    message.end_with?('?')
  end

  def yelling?
    message.upcase == message
  end

  def silence?
    message.nil? || message.empty?
  end
end