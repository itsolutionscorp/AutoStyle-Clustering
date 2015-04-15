class Bob

  def hey(content)
    if content.nil? || content.empty? 
      "Fine. Be that way."
    elsif is_shouting?(content)
      "Woah, chill out!"
    elsif is_asking_question?(content)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def is_shouting?(content)
    content == content.upcase
  end

  def is_asking_question?(content)
    content.rstrip.end_with?('?')
  end

end
