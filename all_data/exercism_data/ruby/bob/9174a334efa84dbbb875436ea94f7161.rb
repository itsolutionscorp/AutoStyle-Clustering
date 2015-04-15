class Bob
  def hey(x)
    if x.upcase == x && x.match(/[A-Za-z]/)
      return 'Woah, chill out!'
    elsif x.split('').last == "?"
      return "Sure."
    elsif x.strip.length == 0
      return "Fine. Be that way!"
    end
    "Whatever."
  end
end
