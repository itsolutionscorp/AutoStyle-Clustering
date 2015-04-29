class Bob

  def hey(string)
    message = MessageResponse.new(string)
    return "Fine. Be that way." if message.blank?
    return 'Woah, chill out!' if message.shouting?(string)
    return "Whatever." if message.ends_with?([".", "!"])
    return "Sure." if message.ends_with?("?")
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
    @message == "" || @message.nil?
  end
  
  def ends_with?(args)
    if args.respond_to?('end_with?')
      @message.end_with?(args)
    else
      stuff = args.collect {|i| @message.end_with?(i)} 
      (stuff.include?(true)) ? true : false
    end
  end

end
