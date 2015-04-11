class Bob
  def hey(string)
    case
    when blank?(string)      then 'Fine. Be that way!'
    when upcase?(string)     then 'Woah, chill out!'
    when a_question?(string) then 'Sure.'
    else                          'Whatever.'
    end
  end

  def blank?(string)
    return true if string.nil?
    string.each_char do |character|
      return false if character != " "
    end
    true
  end

  def upcase?(string)
    string.upcase == string
  end

  def a_question?(string)
    string.end_with? "?"
  end

  def last_character(string)
    string.empty? ? "" : string[-1]
  end
end
