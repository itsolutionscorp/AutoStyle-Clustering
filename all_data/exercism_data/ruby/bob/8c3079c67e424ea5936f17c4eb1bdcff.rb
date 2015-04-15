class Bob
  def hey(string)
    case
    when string.nil?, string.not_saying_anything? then 'Fine. Be that way!'
    when string.yelling?                          then 'Woah, chill out!'
    when string.a_question?                       then 'Sure.'
    else                                               'Whatever.'
    end
  end
end

module TeenagerTalk
  def not_saying_anything?
    self.each_char { |character| return false unless character == " " }
    true
  end

  def yelling?
    self.upcase == self
  end

  def a_question?
    self.end_with? "?"
  end
end

class String; include TeenagerTalk; end
