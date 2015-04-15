class Bob
  def hey(string)
    case
    when say_nothing?(string)   then "Fine. Be that way."
    when string.end_with?('?')  then "Sure."
    when upcase?(string)        then "Woah, chill out!"
    else "Whatever."
    end
  end

  def say_nothing?(string)
    string.nil? || string.empty?
  end

  def upcase?(string)
    string == string.upcase
  end
end
