class Bob
  def hey(string)
    case
    when string.nil? || string.empty? then "Fine. Be that way."
    when string =~ /\?$/              then "Sure."
    when string == string.upcase      then "Woah, chill out!"
    else "Whatever."
    end
  end
end