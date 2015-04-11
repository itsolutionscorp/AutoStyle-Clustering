class Bob
  def hey(string)
    text = ConversationalString.new(string.to_s)

    return 'Fine. Be that way!' if text.blank?

    if text.yelling?
      'Woah, chill out!'
    elsif text.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class ConversationalString < String
  def blank?
    empty?
  end

  def yelling?
    upcase == self
  end

  def question?
    chars.last == '?'
  end
end
