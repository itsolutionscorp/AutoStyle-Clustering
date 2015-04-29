class Bob
  def hey(input)
    if input.strip == ""
      "Fine. Be that way!"
    elsif input.match(/[A-Za-z]/) && input == input.upcase
      "Woah, chill out!"
    elsif input.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end
end
