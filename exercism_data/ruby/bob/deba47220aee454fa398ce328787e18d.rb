class Bob
  def hey(message)
    if addressing?(message)
      return "Fine. Be that way!"
    elsif yelling?(message)
      return "Woah, chill out!"
    elsif question?(message)
      return "Sure."
    else
      return "Whatever."
    end 
  end

  private

    def question?(message)
      message[-1].chr == "?" ? true : false
    end

    def yelling?(message)
      message.each_byte { |b| return false if (b >= 97 and b <= 122) }
      return true
    end

    def addressing?(message)
      message.strip.empty? ? true : false
    end
end
