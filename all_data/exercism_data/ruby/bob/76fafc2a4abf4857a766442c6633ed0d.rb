class Bob
  def hey(content)

    if silent_treatment?(content)
      'Fine. Be that way.'
    elsif shouting?(content)
      'Woah, chill out!'
    elsif asking?(content)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silent_treatment?(content)
    content == ''
  end

  def shouting?(content)
    content == content.upcase
  end

  def asking?(content)
    content.end_with?('?')
  end
end
