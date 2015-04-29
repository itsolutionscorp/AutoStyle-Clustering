class Bob
  def hey(message)
    case message.to_s
      when silent?(message)   then 'Fine. Be that way.'
      when question?(message) then 'Sure.'
      when shouting?(message) then 'Woah, chill out!'
      else                         'Whatever.'
    end
  end

  private
    def question?(message)
      message.end_with?('?')
    end

    def shouting?(message)
      message.upcase == message
    end

    def silent?(message)
      message == ''
    end
end
