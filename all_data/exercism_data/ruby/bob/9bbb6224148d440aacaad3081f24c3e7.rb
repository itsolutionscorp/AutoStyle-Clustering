class Bob
  def hey(remark_text)
    remark = Remark.new(remark_text)

    case
    when remark.silence?  then "Fine. Be that way!"
    when remark.yelling?  then "Woah, chill out!"
    when remark.question? then "Sure."
    else                       "Whatever."
    end
  end

  private
  class Remark
    def initialize(text)
      @text = text
    end

    def silence?
      text.strip.empty?
    end

    def yelling?
      text == text.upcase
    end

    def question?
      text.end_with?('?')
    end

    private
    attr_reader :text
  end
end
