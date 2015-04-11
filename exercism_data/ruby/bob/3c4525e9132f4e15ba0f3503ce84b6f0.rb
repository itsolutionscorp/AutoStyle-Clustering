class Bob
  def hey(remark)
    remark = Remark.new remark
    if remark.upcase?
      'Whoa, chill out!'
    elsif remark.ends_with_question_mark?
      'Sure.'
    elsif remark.empty? or remark.blank?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Remark < String
  def upcase?
    self == self.upcase and self =~ /[A-Z]/
  end

  def ends_with_question_mark?
    self.end_with? '?'
  end

  def blank?
    self.strip.empty?
  end
end
