class Bob
  def hey(message)

    if (message.to_s.empty?)
      return 'Fine. Be that way.'
    end

    if (message.upcase == message)
      return 'Woah, chill out!' 
    end

    if (message.end_with? "?")
      return 'Sure.'
    end

    'Whatever.'
  end
end
