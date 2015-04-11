class Bob

  def hey(statement)
    (is_silence? statement and "Fine. Be that way!") or
    (is_yelling? statement and "Woah, chill out!") or
    (is_question? statement and "Sure.") or
    "Whatever."
  end

  def is_silence?(statement) statement.to_s.strip.empty?; end
  def is_yelling?(statement) statement.upcase == statement; end
  def is_question?(statement) statement.end_with? "?"; end

end
