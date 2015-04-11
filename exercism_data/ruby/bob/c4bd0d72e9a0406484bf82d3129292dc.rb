class Bob

  def hey(message)
    if being_shouted_at?(message)
      'Woah, chill out!'
    elsif being_questioned?(message)
      'Sure.'
    elsif given_silent_treatment?(message)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def being_questioned?(message)
    message.end_with?('?')
  end

  def being_shouted_at?(message)
    contains_letters?(message) && message == message.upcase
  end

  def given_silent_treatment?(message)
    message.strip == ''
  end

  def contains_letters?(message)
    message.scan(/[A-Za-z]/).any?
  end

end
