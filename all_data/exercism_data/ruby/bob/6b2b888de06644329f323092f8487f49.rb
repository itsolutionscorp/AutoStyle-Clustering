class Bob
  def hey(string)
    case
    when say_nothing?(string)   then "Fine. Be that way."
    when ask_question?(string)  then "Sure."
    when yell_at_him?(string)   then "Woah, chill out!"
    else "Whatever."
    end
  end

  def say_nothing?(string)
    string.nil? || string.empty?
  end

  def ask_question?(string)
    string.end_with?('?')
  end

  def yell_at_him?(string)
    string == string.upcase
  end
end
