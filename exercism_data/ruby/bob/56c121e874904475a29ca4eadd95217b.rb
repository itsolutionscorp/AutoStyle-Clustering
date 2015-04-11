class Bob

  def initialize
  end

  def hey(saying)
    if saying.nil? || saying.strip == ""
      "Fine. Be that way!"
    elsif saying == saying.upcase
      "Woah, chill out!"
    elsif saying =~ /\?$/
      "Sure."
    else
      "Whatever."
    end
  end

end
