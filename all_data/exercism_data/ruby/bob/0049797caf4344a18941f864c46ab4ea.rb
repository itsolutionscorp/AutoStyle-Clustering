class Bob

  def hey comment

    if comment

      @comment = comment

      case
      when silent
        'Fine. Be that way.'
      when questioning
        'Sure.'
      when forceful
        'Whatever.'
      when shouting
        'Woah, chill out!'
      else
        'Whatever.'
      end
    else
      'Fine. Be that way.'
    end

  end

private

  def silent
    @comment.empty?
  end

  def forceful
   @comment != @comment.upcase && @comment.end_with?('!')
  end

  def shouting
    @comment == @comment.upcase
  end

  def questioning
    @comment.end_with?('?')
  end

end
