class Bob
  def hey(msg)
    if is_silent?(msg)
      'Fine. Be that way!'
    elsif is_all_caps?(msg)
      'Woah, chill out!'
    elsif is_question?(msg)
      'Sure.'
    else
      "Whatever."
    end
  end

  def is_all_caps?(msg)
    msg && msg.upcase == msg
  end

  def is_question?(msg)
    msg && msg.end_with?('?')
  end

  def is_silent?(msg)
    msg.nil? || msg.strip == ''
  end

  private :is_all_caps?, :is_question?, :is_silent?
end
