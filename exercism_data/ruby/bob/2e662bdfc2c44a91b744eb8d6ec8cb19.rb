class Bob
  def hey(response)
    if response.nil? || response.strip.length == 0
      "Fine. Be that way!"
    elsif response == response.upcase
      "Woah, chill out!"
    elsif response.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end
end
