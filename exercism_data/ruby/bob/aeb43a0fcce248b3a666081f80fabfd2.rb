class Bob
  def hey data
    return 'Fine. Be that way!' if is_silence data
    return 'Woah, chill out!' if is_yelling data
    return 'Sure.' if is_question data
    return 'Whatever.'
  end

  def is_silence(data)
    return not data or (data.is_a?(String) and data.strip == '')
  end

  def is_yelling(data)
    return data.upcase == data
  end

  def is_question(data)
    return data.end_with?('?')
  end
end
