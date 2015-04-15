class Bob
  def hey(something)
    case
    when blank?(something)
      'Fine. Be that way!'
    when shouting?(something)
      'Woah, chill out!'
    when question?(something)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def blank?(string)
    string.strip.empty?
  end

  def shouting?(string)
    has_characters?(string) && upcase?(string)
  end

  def has_characters?(string)
    string =~ /[A-z]/
  end

  def upcase?(string)
    string == string.upcase
  end

  def question?(string)
    string.end_with?('?')
  end
end
