class Bob
  def hey(message)
    if silent?(message)
      return "Fine. Be that way!"
    elsif all_caps?(message)
      return "Woah, chill out!"
    elsif question?(message)
      return "Sure."
    else
      return "Whatever."
    end
  end

  private
  def all_caps?(message)
    message.upcase == message
  end

  def silent?(message)
    message.gsub("\n","").match(/^\s*$/) != nil
  end

  def question?(message)
    message.chomp[-1] == '?'
  end
end
