class Bob

  def hey(reply)
    case
    when silent?(reply)
      "Fine. Be that way."
    when loud?(reply)
      "Woah, chill out!"
    when interrogative?(reply)
      "Sure."
    else
      "Whatever."
    end
  end

  private

    def silent?(reply)
      reply.nil? || reply.length == 0
    end

    def loud?(reply)
      # !reply.match(/[a-z]/)
      reply == reply.upcase
    end

    def interrogative?(reply)
      reply[-1] == "?"
    end

end
