class Bob
  def hey(remark_text)
    case Remark.new(remark_text)
    when ->(r) { r.silence? }  then "Fine. Be that way!"
    when ->(r) { r.yelling? }  then "Woah, chill out!"
    when ->(r) { r.question? } then "Sure."
    else                            "Whatever."
    end
  end

  private
  class Remark < String
    def silence?
      self.strip.empty?
    end

    def yelling?
      self == self.upcase
    end

    def question?
      self.end_with?('?')
    end
  end
end
