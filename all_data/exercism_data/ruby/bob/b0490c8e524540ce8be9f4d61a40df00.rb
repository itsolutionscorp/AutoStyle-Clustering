class Bob

  def hey argument
    if argument.delete(" ").length > 0 && argument == argument.upcase
      "Woah, chill out!"
    elsif argument.delete(" ").empty?
      "Fine. Be that way!"
    elsif argument.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end

end
