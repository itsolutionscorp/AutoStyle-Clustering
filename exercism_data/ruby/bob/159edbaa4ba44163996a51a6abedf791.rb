class Bob
  def hey content
    case content
    when -> (c) { c.strip.empty? }
      "Fine. Be that way!"
    when -> (c) { c.index(/[a-z]/).nil? and !c.index(/[A-Z]/).nil? }
      "Woah, chill out!"
    when -> (c) { c.end_with? "?"}
      "Sure."
    else
      "Whatever."
    end
  end
end
