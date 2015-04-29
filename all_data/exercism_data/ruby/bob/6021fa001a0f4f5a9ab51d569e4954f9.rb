class Bob
  def hey(string)
    remark = Remark.new(string)
    case
    when remark.shouting?
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

class Remark
  def initialize(remark)
    @remark = remark.strip
  end

  def shouting?
     @remark == @remark.upcase && !silence?
  end

  def question?
     @remark.end_with?('?') && !shouting?
  end

  def silence?
    @remark.empty?
  end
end
