class Bob
  def hey(msg)
    if empty?(msg)
      "Fine. Be that way!"
    elsif shout?(msg)
      "Woah, chill out!"
    elsif query?(msg)
      "Sure."
    else
      "Whatever."
    end
  end

private
  def query?(msg)
    msg.end_with? "?"
  end

  def shout?(msg)
    msg.upcase == msg
  end

  def empty?(msg)
    msg.gsub(/ /, '') == ''
  end

end
