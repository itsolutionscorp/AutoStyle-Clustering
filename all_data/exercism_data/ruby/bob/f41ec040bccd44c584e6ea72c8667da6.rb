class Bob

  def hey(s)
    case s
    when /\A\s*\z/          # Silence
      "Fine. Be that way!"
    when /\A[^a-z0-9]*\z/,  # Shouting 1
         /\A[^a-z]*!\z/     # Shouting 2
      "Woah, chill out!"
    when /\?\z/             # Question
      "Sure."
    else
      "Whatever."
    end
  end

end
