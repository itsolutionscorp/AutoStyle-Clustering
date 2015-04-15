class Bob
  def hey(message)
    case message.to_s
      when ''             then 'Fine. Be that way.'
      when /\?\Z/         then 'Sure.'
      when message.upcase then 'Woah, chill out!'
      else                     'Whatever.'
    end
  end
end
