class Bob
  def hey(string)
    case
    when not_saying_anything?(string) then 'Fine. Be that way!'
    when yelling?(string)             then 'Woah, chill out!'
    when a_question?(string)          then 'Sure.'
    else                                   'Whatever.'
    end
  end

  def not_saying_anything?(string)
    return true if string.nil?
    string.each_char do |character|
      return false if character != " "
    end
    true
  end

  def yelling?(string)
    string.upcase == string
  end

  def a_question?(string)
    string.end_with? "?"
  end
end
