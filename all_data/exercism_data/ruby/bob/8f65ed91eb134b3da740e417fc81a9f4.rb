class Bob
  def hey(talk)
    if is_speech_empty?(talk)
      "Fine. Be that way!"
    elsif is_speech_shout?(talk)
      "Woah, chill out!"
    elsif is_speech_question?(talk)
      "Sure."
    else
      "Whatever."
    end
  end


  private
  def is_speech_empty?(talk)
    talk.to_s.strip.empty?
  end

  private
  def is_speech_shout?(talk)
    talk == talk.upcase
  end

  private
  def is_speech_question?(talk)
    talk.end_with?("?")
  end
end
