class Bob

  def hey(input)
    if  input.empty? || input =~ /^\s+$/
      "Fine. Be that way!"
    elsif input === input.upcase
      "Woah, chill out!"
    elsif input.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end

end
