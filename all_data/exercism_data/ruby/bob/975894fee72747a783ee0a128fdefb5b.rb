class Bob

  def hey comment
    if silence?(comment)
      'Fine. Be that way!'
    elsif yelling?(comment)
      'Woah, chill out!'
    elsif question?(comment)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence?(comment)
    comment.to_s.strip.empty?
  end

  def yelling?(comment)
    comment.upcase == comment
  end

  def question?(comment)
    comment.end_with?('?')
  end
end
