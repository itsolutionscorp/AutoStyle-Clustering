class Bob

  def hey(text)
    if text.nil? or text.empty?
      silence
    elsif text.end_with?('?')
      ask
    elsif text.upcase == text
      shout
    else
      ignore
    end
  end
  
  private

  def shout
    "Woah, chill out!"
  end

  def ask
    "Sure."
  end

  def silence
    "Fine. Be that way."
  end

  def ignore
    "Whatever."
  end

end
