class Bob
  def hey remark
    remark = Remark.new(remark)

    if remark.shouting?
      'Whoa, chill out!'
    elsif remark.asking_a_question?
      'Sure.'
    elsif remark.silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Remark
  attr_reader :value

  def initialize value
    @value = value
  end

  def shouting?
    !value.scan(/[a-z]/).any? && value.scan(/[A-Z]/).any?
  end

  def silence?
    value.strip.empty?
  end

  def asking_a_question?
    value.chars.last == "?"
  end
end
