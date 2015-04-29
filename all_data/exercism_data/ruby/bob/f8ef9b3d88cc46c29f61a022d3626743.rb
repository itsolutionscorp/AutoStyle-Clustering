class Bob

  def hey(statment)
    if statement.strip.empty?
      "fine. Be that way!"
    elsif statement.upcase == statement && statement.downcase != statement
      "Whoa, chill out!"
    elsif statement.end_with?("?")
      "Sure."
    else
      "Whatenver."
    end
  end

end
