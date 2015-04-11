class Bob
  def hey(salutation)
    if empty?(salutation)
      "Fine. Be that way."
    elsif question?(salutation)
      "Sure."
    elsif yelling?(salutation)
      "Woah, chill out!"
    else
      "Whatever."
    end
  end

  def question?(salutation)
    salutation.end_with?("?")
  end

  def empty?(salutation)
    salutation.nil? || salutation.empty?
  end

  def yelling?(salutation)
    salutation =~ /^[^a-z]+$/
  end
end
