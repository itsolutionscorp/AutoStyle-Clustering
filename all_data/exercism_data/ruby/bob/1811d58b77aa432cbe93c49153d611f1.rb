class Bob
  def hey(string)
    case
    when silence?(string)
        'Fine. Be that way!'
    when only_numbers_question?(string)
      'Sure.'
    when only_numbers?(string)
      'Whatever.'
    when yelled?(string)
      'Woah, chill out!'
    when question?(string)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def silence?(string)
    string.strip.empty?
  end

  def only_numbers_question?(string)
    only_numbers?(string) && question?(string)
  end

  def only_numbers?(string)
    begin
      Integer(string.gsub(/(,|\s|\?)/, ''))
      true
    rescue
      false
    end
  end

  def yelled?(string)
    string == string.upcase
  end

  def question?(string)
    string[-1] == '?'
  end

  def yelled_question?(string)
    yelled? && question?
  end
end
