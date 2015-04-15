class Bob
  def hey saying
    router saying.to_s
  end

  private
  def router saying
    case saying
    when ''
      silence
    when saying.upcase
      shout
    when /\?\Z/
      question
    when /[!]\Z/
      forceful
    else
      normal
    end
  end

  def normal
    "Whatever."
  end

  def shout
    "Woah, chill out!"
  end

  def question
    "Sure."
  end

  def forceful
    "Whatever."
  end

  def silence
    "Fine. Be that way."
  end
end
