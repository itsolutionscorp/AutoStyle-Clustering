#new to ruby...go easy :)

class Bob

  def initialize
    #not needed
  end

  def hey(msg)
    @message = msg
    value = ""

    if @message.strip.empty?
      value = "Fine. Be that way!"
    elsif @message.eql?(@message.upcase) && @message =~ /[A-Z]/ 
      value = "Woah, chill out!"
    elsif @message.end_with?('?')
      value = "Sure."
    else
      value = "Whatever."
    end
    value
  end

end
