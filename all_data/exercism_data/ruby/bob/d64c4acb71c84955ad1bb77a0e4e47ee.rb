class Bob

  def initialize
  end

  def hey(input)
    case
    when shouted_at?(input)
      "Woah, chill out!"
    when questioned?(input)
      "Sure."
    when ignored?(input)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

  def questioned?(input)
    input.end_with?("?")
  end

  def shouted_at?(input)
    input == input.upcase && !(input == input.downcase)
  end

  def ignored?(input)
    input.strip.empty?
  end

end
