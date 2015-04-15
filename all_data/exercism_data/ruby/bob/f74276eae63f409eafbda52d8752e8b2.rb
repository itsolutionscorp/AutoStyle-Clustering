class Bob

  def hey(line)
    case
    when shout?(line)
      "Woah, chill out!"
    when question?(line)
      "Sure."
    when silence?(line)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

  def shout?(line)
    line =~ /[A-Z]/ && line == line.upcase
  end

  def question?(line)
    line =~ /\?\z/
  end

  def silence?(line)
    line.strip.size == 0
  end

end
