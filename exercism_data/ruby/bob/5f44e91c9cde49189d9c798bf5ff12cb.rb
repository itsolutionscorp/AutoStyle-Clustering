class Bob
  def hey(remark_text)
    remark   = Remark.new(remark_text)

    if    remark.silence?  then "Fine. Be that way!"
    elsif remark.yelling?  then "Woah, chill out!"
    elsif remark.question? then "Sure."
    else                        "Whatever."
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
