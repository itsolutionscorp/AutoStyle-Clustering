class Bob
  def initialize
    
  end

  def hey(str)
    return "Fine. Be that way!" if str.strip.empty?

    letters = str.scan(/[a-zA-Z]./).join
    return "Woah, chill out!" if letters == letters.upcase && !letters.empty?
    return "Sure." if str.chars.to_a.last == "?"
    "Whatever."
  end

end
