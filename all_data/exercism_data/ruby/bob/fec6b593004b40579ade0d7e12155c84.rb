class Bob
  def hey(greeting)
    if greeting.squeeze.chop.empty?
      "Fine. Be that way!"
    elsif greeting.upcase == greeting
      "Woah, chill out!"
    elsif greeting.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end
end
