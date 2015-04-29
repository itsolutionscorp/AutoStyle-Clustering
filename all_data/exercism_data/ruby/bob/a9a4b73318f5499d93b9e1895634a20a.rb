class Bob

  def hey comment

    case
    when silent?(comment)
      'Fine. Be that way.'
    when questioning?(comment)
      'Sure.'
    when shouting?(comment)
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end

private

  def silent? comment
    comment.to_s.empty?
  end

  def shouting? comment
    comment == comment.upcase
  end

  def questioning? comment
    comment.end_with?('?')
  end

end
