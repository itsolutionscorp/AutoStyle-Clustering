class Bob

  ## Includes letter characters, and all of which are capital
  def shouting(str)
    letters = str.gsub(/[^a-zA-Z]/, "")
    letters.length > 0 && letters == letters.upcase
  end

  def hey(msg)
    case msg
    when ->(msg) { shouting(msg) }
      "Woah, chill out!"
    when /\?\Z/ ## question - entire string ends with a question mark
      "Sure."
    when /\A\s*\Z/ ## blank - entire string is only whitespace chars
      'Fine. Be that way!'
    else
      "Whatever."
    end
  end
end
