class Bob
  def initialize
  end

  def hey( input )
    case input
    when /\A *\Z/ # just spaces on all lines
      "Fine. Be that way!"
    when /^[^a-z]*[A-Z]{2,}[^a-z]*$/  # no small letters, and at least two uppercase letters somewhere
      "Woah, chill out!"
    when /\?\Z/  # a question (ends with a question mark on the last line)
      "Sure."
    else 
      "Whatever."
    end
  end
end
