class Bob
  def hey(greeting)
    if  greeting.strip.empty?
      "Fine. Be that way!"
    elsif greeting.upcase == greeting
      "Woah, chill out!"
    elsif greeting[-1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
