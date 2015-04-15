class Bob
  attr_reader :comment

  def hey comment
    @comment = comment

    if silence?
      'Fine. Be that way!'
    elsif yelling?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence?
    comment.to_s.strip.empty?
  end

  def yelling?
    comment.upcase == comment
  end

  def question?
    comment.end_with?('?')
  end
end
