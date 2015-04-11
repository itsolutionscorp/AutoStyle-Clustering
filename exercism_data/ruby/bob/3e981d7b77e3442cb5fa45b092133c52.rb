class Bob
  def hey(greeting=nil)
    @greeting = greeting
    if detects_silence?
      "Fine. Be that way!"
    elsif detects_shouting?
      "Woah, chill out!"
    elsif detects_interrogation?
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def detects_silence?
    @greeting.to_s.strip.empty?
  end

  def detects_shouting?
    @greeting.upcase == @greeting
  end

  def detects_interrogation?
    @greeting.end_with?("?")
  end
end
