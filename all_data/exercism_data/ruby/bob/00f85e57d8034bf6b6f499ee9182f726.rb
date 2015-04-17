class Bob

  def hey(content)
    if nothing?(content)
      "Fine. Be that way."
    elsif shouting?(content)
      "Woah, chill out!"
    elsif asking_question?(content)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def nothing?(content)
    content.nil? || content.empty?
  end

  def shouting?(content)
    content == content.upcase
  end

  def asking_question?(content)
    content.rstrip.end_with?('?')
  end

end