class Bob

  attr_accessor :msg

  def hey(msg)
    self.msg = msg

    if is_telling_nothing?
      answer_for_nothing
    elsif is_yelling?
     answer_for_yelling
    elsif is_asking?
      answer_for_question
    else
      answer_for_anything_else
    end
  end

  private

  def is_telling_nothing?
    msg.gsub(/\s/, "") == ""
  end

  def is_yelling?
    msg.upcase == msg
  end

  def is_asking?
    msg.match(/.*\?\z/)
  end

  def answer_for_nothing
    "Fine. Be that way!"
  end

  def answer_for_yelling
    "Woah, chill out!"
  end

  def answer_for_question
    "Sure."
  end

  def answer_for_anything_else
    "Whatever."
  end

end
