module Remarks
  def silent?
    empty?
  end

  def question?
    end_with? '?'
  end

  def shouting?
    self != downcase && self == upcase
  end
end

class Bob
  def hey(text)
    remark = text.strip.extend Remarks

    case
    when remark.silent?
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
