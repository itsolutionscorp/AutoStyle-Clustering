class Bob
  def hey(arg)
    case arg
    when /^ *$/;      "Fine. Be that way!"
    when /^[^a-z]+$/; "Woah, chill out!"
    when /\?$/;       "Sure."
    else              "Whatever."
    end
  end
end
