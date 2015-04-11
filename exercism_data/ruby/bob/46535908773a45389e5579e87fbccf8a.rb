class Bob
  def hey(talk)
    if empty?(talk)
      "Fine. Be that way!"
    elsif shout?(talk)
      "Woah, chill out!"
    elsif question?(talk)
      "Sure."
    else
      "Whatever."
    end
  end


  private
  def empty?(talk)
    talk.to_s.strip.empty?
  end

  private
  def shout?(talk)
    talk == talk.upcase
  end

  private
  def question?(talk)
    talk.end_with?("?")
  end
end
