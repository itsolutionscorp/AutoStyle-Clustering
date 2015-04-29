class Bob

  def hey(reply)
    @reply = reply
    case
    when silent?
      "Fine. Be that way."
    when loud?
      "Woah, chill out!"
    when interrogative?
      "Sure."
    else
      "Whatever."
    end
  end

  private

    def silent?
      @reply.nil? || @reply.length == 0
    end

    def loud?
      # !@reply.match(/[a-z]/)
      @reply == @reply.upcase
    end

    def interrogative?
      @reply[-1] == "?"
    end

end
