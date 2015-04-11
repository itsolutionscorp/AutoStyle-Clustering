class Bob
  def hey(string)
    last_char = string[-1]

    if string.strip.empty?
      "Fine. Be that way!"
    elsif string.upcase == string && string =~ /[A-Z]/
      "Whoa, chill out!"
    elsif string[-1] == '?'
      "Sure."
    else
      "Whatever."
    end
  end
end
