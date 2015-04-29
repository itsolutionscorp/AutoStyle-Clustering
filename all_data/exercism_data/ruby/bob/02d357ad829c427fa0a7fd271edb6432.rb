class Remark < Struct.new(:what_was_said)
  def blank?
    what_was_said == '' || what_was_said.nil?
  end

  def question?
    what_was_said.end_with? '?'
  end

  def shouting?
    what_was_said == what_was_said.upcase
  end  
end

class Bob
  def hey(what_was_said)
    remark = Remark.new(what_was_said)

    return "Fine. Be that way!" if remark.blank?
    return "Woah, chill out!" if remark.shouting?
    return "Sure." if remark.question?

    "Whatever."
  end
end
