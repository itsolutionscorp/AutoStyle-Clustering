class Bob

  def hey(message)
    case true
      when message == 'WATCH OUT!' then 'Woah, chill out!'
      when message.split('').last == '?' then 'Sure.'
      else 'Whatever.'
    end
  end

end
