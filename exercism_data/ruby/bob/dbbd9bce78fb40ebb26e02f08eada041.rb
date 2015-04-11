class Bob
  def hey(greeting=nil)
    @greeting = greeting
    if detects_silence?
      "Fine. Be that way!"
    elsif @greeting.upcase == @greeting
      "Woah, chill out!"
    elsif @greeting.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def detects_silence?
    case @greeting
    when nil
      true
    when /\A\s*\z/
      true
    else
      false
    end
  end
end
