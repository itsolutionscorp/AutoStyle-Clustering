class Bob
  def hey(comment)
    case comment
    when /\A\s*\z/
      "Fine. Be that way!"
    when /\A[A-Z0-9,%^\*@#\$\(\?! ]+[\?!]?\z/
      "Woah, chill out!"
    when /\?\z/
      "Sure."
    else
      "Whatever."
    end
  end
end
