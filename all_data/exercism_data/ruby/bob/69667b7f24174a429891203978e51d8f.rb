# For feedback on your submission visit http://exercism.io/submissions/796d7c29ba28a4c80ed4d263.
class Bob

  def hey(message)
    teenage_response(message)
  end

  private

  def teenage_response(message)
    if nothing?(message) then 'Fine. Be that way!'
    elsif yelling?(message) then 'Woah, chill out!'
    elsif question?(message) then 'Sure.'
    else 'Whatever.'
    end
  end

  def nothing?(message)
    message.strip == ''
  end

  def yelling?(message)
    /[A-Z]/ =~ message && message.upcase == message
  end

  def question?(message)
    message.end_with?('?')
  end

end
