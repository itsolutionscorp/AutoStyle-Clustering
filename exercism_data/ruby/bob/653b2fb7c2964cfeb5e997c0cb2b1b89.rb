class Bob
  def hey remark
    Remark.class_eval do
      alias_method :is_yelling?, :upcase?
      alias_method :is_asking?, :ends_with_question_mark?
      alias_method :is_silent?, :blank?
    end

    remark = Remark.new remark
    if remark.is_yelling?
      'Whoa, chill out!'
    elsif remark.is_asking?
      'Sure.'
    elsif remark.is_silent?
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
