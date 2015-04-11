class Bob
  def hey(message)
    @heard = message

    if heard_nothing?
      'Fine. Be that way!'
    elsif heard_yelling?
      'Woah, chill out!'
    elsif heard_question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def heard_question?
    @heard.end_with?('?')
  end

  def heard_nothing?
    @heard.strip.empty?
  end

  def heard_yelling?
    is_upcase? && has_letters?
  end

  def is_upcase?
    @heard == @heard.upcase
  end

  def has_letters?
    !@heard.gsub(/[^a-z]/i, '').empty?
  end
end
