class Bob
  def hey(text = '')
    case 
    when silent?(text)
      'Fine. Be that way!'
    when loud?(text)
      'Woah, chill out!'
    when question?(text)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silent?(string)
    string.strip.empty?
  end

  def loud?(string)
    string.upcase == string
  end

  def question?(string)
    string.end_with?("?")
  end

end
