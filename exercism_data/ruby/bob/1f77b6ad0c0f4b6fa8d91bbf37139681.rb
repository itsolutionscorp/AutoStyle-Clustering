class Bob

  def hey(message)
    if nothing?(message)
      "Fine. Be that way."
    elsif yell?(message)
      "Woah, chill out!"
    elsif question?(message)
      "Sure."
    else 
      "Whatever."
    end
  end


  private

    def nothing?(message)
      message.to_s.length == 0
    end

    def question?(message)
      message[-1] == "?" 
    end

    def yell?(message)
      message.to_s.upcase == message
    end

end
