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

  Question = %r[ \? \Z ]x
  Silence  = %r[ \A \p{Space}* \Z ]x
  Yelling  = %r[ \A \p{^Lower}* \p{Upper} \p{^Lower}* \Z ]x

end
