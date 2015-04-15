class Bob
  def hey(message)
    if message.to_s == ''
      return 'Fine. Be that way.'
    elsif message.index(/[a-z]/).nil?
      return 'Woah, chill out!'
    elsif !message.index(/\?$/).nil?
      return 'Sure.'
    end
    return 'Whatever.'
  end
end
