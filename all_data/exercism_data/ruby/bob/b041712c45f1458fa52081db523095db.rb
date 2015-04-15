class Bob
  def hey remark
    remark = Remark.new remark
    if is_a_yell remark
      'Whoa, chill out!'
    elsif is_a_question remark
      'Sure.'
    elsif is_a_silent remark
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private
    def is_a_yell remark
      return remark.upcase?
    end

    def is_a_question remark
      return remark.ends_with_question_mark?
    end

    def is_a_silent remark
      return remark.blank?
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
