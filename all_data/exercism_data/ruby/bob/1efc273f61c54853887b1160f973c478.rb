class Bob
  def hey(message)
    if yell?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    elsif silence?(message)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private
    def yell?(message)
      message.eql?(message.upcase) and not message.eql?(message.downcase)
    end

    def question?(message)
      message.end_with?('?')
    end

    def silence?(message)
      message.lstrip.length == 0
    end
end
