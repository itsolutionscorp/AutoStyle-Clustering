class Bob
  def hey(salutation)
    empty?(salutation)      ||
      question?(salutation) ||
      yelling?(salutation)  ||
      default
  end

  def question?(salutation)
    salutation.end_with?("?") && "Sure."
  end

  def empty?(salutation)
    (salutation.nil? || salutation.empty?) && "Fine. Be that way."
  end

  def yelling?(salutation)
    salutation =~ /^[^a-z]+$/ && "Woah, chill out!"
  end

  def default
    "Whatever."
  end
end
