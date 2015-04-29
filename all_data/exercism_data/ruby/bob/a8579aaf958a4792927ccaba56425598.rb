class Bob

  def hey(reply)
    case
    when reply_is_silent?(reply)
      "Fine. Be that way."
    when reply_is_loud?(reply)
      "Woah, chill out!"
    when reply_is_a_question?(reply)
      "Sure."
    else
      "Whatever."
    end
  end

  private

    def reply_is_silent?(reply)
      true if reply.nil? || reply.length == 0
    end

    def reply_is_loud?(reply)
      true if !reply.match(/[a-z]/)
    end

    def reply_is_a_question?(reply)
      true if reply[-1] == "?"
    end

end
