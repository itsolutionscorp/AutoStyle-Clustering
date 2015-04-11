class Bob
  def hey(string)
    if (string == string.upcase) && (string =~ /[A-Z]/)
      "Woah, chill out!"
    elsif string.end_with?("?")
      "Sure."
    elsif string.strip.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
