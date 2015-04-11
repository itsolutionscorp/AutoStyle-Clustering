class Bob
  attr_reader :message

  def hey(message)
    @message = message

    if addressing?
      return "Fine. Be that way!"
    elsif yelling?
      return "Woah, chill out!"
    elsif questioning?
      return "Sure."
    end

    return "Whatever."
  end

  private

    def questioning?
      return message.byteslice(-1) == "?" ? true : false
    end

    def yelling?
      message.each_byte { |b| return false if (b >= 97 and b <= 122) }
      return true
    end

    def addressing?
      return message.strip.empty? ? true : false
    end
end
