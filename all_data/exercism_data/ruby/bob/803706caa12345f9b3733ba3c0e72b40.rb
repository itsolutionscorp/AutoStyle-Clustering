class Bob

  def hey(msg)
    if all_caps? msg
      'Woah, chill out!'
    elsif question? msg
      'Sure.'
    elsif silence? msg
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def all_caps?(string)
    (string.upcase == string) and (string.downcase != string)
  end

  def question?(string)
    string.chars.pop == '?'
  end

  def silence?(string)
    string.strip == ''
  end
end
