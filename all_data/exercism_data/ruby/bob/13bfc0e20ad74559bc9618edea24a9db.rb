class Bob

  def hey(input)
    if /\w/.match(input) == nil
      'Fine. Be that way!'
    elsif input == input.upcase
      'Woah, chill out!'
    elsif input[-1] == "?"
      'Sure.'
    else
      "Whatever."
    end
  end

end
