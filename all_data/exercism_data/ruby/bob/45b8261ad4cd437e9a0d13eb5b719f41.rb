class Remark
  def initialize(remark)
    @remark = remark
  end

  def all_caps?
     /\A[^a-z]+\z/ =~ @remark && !silence?
  end

  def question?
     /\?\z/ =~ @remark
  end

  def silence?
    /\A\s*\z/ =~ @remark
  end
end

class Bob
  def hey(string)
    remark = Remark.new(string)
    case
    when remark.all_caps?
      'Woah, chill out!'
    when remark.question?
      'Sure.'
    when remark.silence?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
