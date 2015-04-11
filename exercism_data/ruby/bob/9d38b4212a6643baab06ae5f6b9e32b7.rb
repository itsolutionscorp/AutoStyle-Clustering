class Bob
  def hey(message)
    case message
    when /\A\s*\Z/ then "Fine. Be that way!"
    when ->(m) { m.upcase == m } then "Woah, chill out!"
    when /\?\Z/ then "Sure."
    else "Whatever."
    end
  end
end
