class Bob
  def hey(input)
    remark = Remark.new(input)
    if remark.empty?
      "Fine. Be that way!"
    elsif remark.yelling?
      "Woah, chill out!"
    elsif remark.question?
      "Sure."
    else
      "Whatever."
    end
  end

  class Remark
    def initialize(remark)
      @remark = remark.to_s.strip
    end

    def empty?
      @remark.empty?
    end

    def yelling?
      @remark == @remark.upcase
    end

    def question?
      @remark.end_with? "?"
    end
  end
end
