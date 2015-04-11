class Bob
  def hey(line)
    case
    when line =~ /[A-Z]/ && line == line.upcase
      "Woah, chill out!"
    when line =~ /\?\z/
      "Sure."
    when line.strip.size == 0
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
