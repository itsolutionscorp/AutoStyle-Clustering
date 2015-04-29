class Bob
  IS_SILENT = /^$/
  IS_YELLING = /^[^a-z]*$/
  IS_QUESTIONING = /^.*\?$/

  INSULTED = "Fine. Be that way."
  SUPRISED = "Woah, chill out!"
  ANSWER = "Sure."
  BLAH = "Whatever."

  def hey msg
    case msg.to_s
    when IS_SILENT then INSULTED
    when IS_YELLING then SUPRISED
    when IS_QUESTIONING then ANSWER
    else BLAH
    end
  end
end
