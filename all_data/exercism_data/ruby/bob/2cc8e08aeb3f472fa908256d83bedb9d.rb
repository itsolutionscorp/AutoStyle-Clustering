class Bob
  def hey(msg)
    @msg = msg
    if yelling?
      "Woah, chill out!"
    elsif asking?
      "Sure."
    elsif silent?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def yelling?
    @msg.upcase == @msg and !silent?
  end

  def silent?
    @msg.gsub(/\s+/, "") == ''
  end

  def asking?
    @msg =~ /\?\z/
  end
end
