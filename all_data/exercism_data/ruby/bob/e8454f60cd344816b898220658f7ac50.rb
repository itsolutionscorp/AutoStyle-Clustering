class Bob

  def hey(message)
    @message = MessageResponse.new(message)
    return "Fine. Be that way." if @message.blank?
    return 'Woah, chill out!' if @message.shouting?(message)
    return "Whatever." if @message.ends_with?(".")
    return "Sure." if @message.ends_with?("?")
    return "Whatever." if @message.ends_with?("!")
  end

end


class MessageResponse

  def initialize(string)
    @message = string
  end

  def shouting?(string)
    @message.upcase == string
  end

  def blank?
    if @message == "" || @message.nil?
      return true
    else
      return false
    end
  end
  
  def ends_with?(character)
    @message[@message.size - 1] == character
  end

end
