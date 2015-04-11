class Bob

  def hey(content)
    if nil_or_empty?(content)
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

  def nil_or_empty?(content)
    content.nil? || content.empty?
  end

  def shouting?(content)
    content == content.upcase
  end

  def asking_question?(content)
    content.rstrip.end_with?('?')
  end

end
