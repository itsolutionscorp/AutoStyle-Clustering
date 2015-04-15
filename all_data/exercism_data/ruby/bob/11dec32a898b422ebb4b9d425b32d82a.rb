class Bob

  def hey statement
    case statement
    when Silence
      "Fine. Be that way!"
    when Yelling
      "Woah, chill out!"
    when Question
      "Sure."
    else
      "Whatever."
    end
  end

  Question = ->(statement) { statement.end_with? "?" }
  Silence  = ->(statement) { statement.strip.empty? }
  Yelling  = ->(statement) { not statement.match /\p{Lower}/ and statement.match /\p{Upper}/ }

end
