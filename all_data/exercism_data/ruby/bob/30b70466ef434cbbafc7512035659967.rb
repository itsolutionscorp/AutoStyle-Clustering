class Bob

  def hey msg
    return "Fine. Be that way." if not msg or msg.empty?
    return "Woah, chill out!"   if msg =~ /^[^a-z]+$/
    return "Whatever."          if msg !~ /[?]\Z/
    "Sure."
  end

end
