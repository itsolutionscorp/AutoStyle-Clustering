class Bob
  attr_reader :speak, :mood

  def hey(speak)
    reply_to(speak)
  end

  private

  def reply_to(speak)
    @speak = speak
    set_mood
    reply
  end

  def set_mood
    @mood = if all_caps?
      :angry
    elsif blank?
      :insulted
    elsif question?
      :stupid
    else
      :unimpressed
    end
  end

  def reply
    replies.fetch(mood)
  end

  def replies
    {
      unimpressed: "Whatever.",
      angry: "Woah, chill out!",
      stupid: "Sure.",
      insulted: "Fine. Be that way!"
    }
  end

  def question?
    speak.end_with?('?')
  end

  def all_caps?
    contains_characters? && speak == speak.upcase
  end

  def blank?
    speak.strip.empty?
  end

  def contains_characters?
    speak.match(/[a-zA-Z]/)
  end
end
