class Bob
  def hey(x)
    case
    when x.strip.empty?
      "Fine. Be that way!"
    when x =~ /[A-Z]/ && x.upcase == x
      "Woah, chill out!"
    when x.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end
end
