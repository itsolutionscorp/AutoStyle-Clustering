class Bob

  def hey(message)
    if message.empty?
      return 'Fine. Be that way.'
    elsif !message.match('[a-z]+')
      return "Woah, chill out!"
    elsif message.match('\?$')
      return "Sure."
    else
      return "Whatever."
    end
  end
end
