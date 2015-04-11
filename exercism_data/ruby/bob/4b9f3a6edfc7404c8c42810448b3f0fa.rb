class Bob
  def hey(text)
    remark = Remark.new(text)
    case
    when remark.silence?
      'Fine. Be that way!'
    when remark.shouting?
      'Whoa, chill out!'
    when remark.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Remark
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def silence?
    text.strip.empty?
  end

  def shouting?
    text == text.upcase && text != text.downcase
  end

  def question?
    text.end_with?('?')
  end
end
