class Bob
  def hey(input)
    remark = Remark.new input
    if remark.nothing?
      'Fine. Be that way.'
    elsif remark.yelling?
      'Woah, chill out!'
    elsif remark.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  class Remark
    attr_reader :remark
    def initialize(remark)
      @remark = remark
    end

    def nothing?
      remark.to_s.empty?
    end

    def yelling?
      remark.upcase == remark
    end

    def question?
      remark.end_with? '?'
    end
  end
end
