class Bob
  def hey(msg)
    if yelling?(msg)
      "Woah, chill out!"
    elsif asking?(msg)
      "Sure."
    elsif silent?(msg)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def yelling?(msg)
    msg.upcase == msg and !silent?(msg)
  end

  def silent?(msg)
    msg.gsub(/\s+/, "") == ''
  end

  def asking?(msg)
    msg =~ /\?\z/
  end
end
