class Bob

  SAYINGS = {
    chill: "Whoa, chill out!",
    anything: "Whatever.",
    silence: "Fine. Be that way!",
    question: "Sure."
  }
  def hey(text)
    if  text.upcase == text && text.match(/[a-zA-Z]/)
      SAYINGS.fetch(:chill)
    elsif text[-1] == '?'
      SAYINGS.fetch(:question)
    elsif text.strip.empty?
      SAYINGS.fetch(:silence)
    else
      SAYINGS.fetch(:anything)
    end
  end
end
