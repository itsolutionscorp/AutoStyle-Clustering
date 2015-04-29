class Bob

  def hey(string)
    return 'Fine. Be that way!'  if silent?(string)
    return 'Woah, chill out!'    if yelling?(string)
    return 'Sure.'               if asking_question?(string)
    
    'Whatever.'
  end

private

  def asking_question?(string)
    string.end_with?('?')
  end

  def silent?(string)
    string.strip.empty?
  end

  def yelling?(string)
    !string[/[[:lower:]]/] unless string.gsub(/[^a-zA-Z]/, '').empty?
  end

end
