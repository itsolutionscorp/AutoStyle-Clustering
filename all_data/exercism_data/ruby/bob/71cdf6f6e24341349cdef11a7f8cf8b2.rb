class Bob

  def hey comment
    if comment.to_s.strip == ''
      'Fine. Be that way!'
    elsif comment.upcase == comment
      'Woah, chill out!'
    elsif comment[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end

end
