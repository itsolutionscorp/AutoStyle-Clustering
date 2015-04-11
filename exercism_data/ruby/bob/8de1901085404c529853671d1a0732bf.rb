class Bob
  def hey(message)
    map_response(message).new.to_s
  end

  private
  def map_response(message)
    response_type = responses.detect do |check, response_type|
      send(check, message.to_s)
    end
    response_type ? response_type[1] : Indifference
  end

  def responses
    {
      :silence?  => Sulkiness,
      :yelling? => Indignance,
      :question? => Unhelpfulness
    }
  end

  def yelling?(message)
    message.upcase == message
  end

  def question?(message)
    message =~ /\?\Z/
  end

  def silence?(message)
    message == ""
  end

  class Indifference
    def to_s
      "Whatever."
    end
  end

  class Indignance
    def to_s
      "Woah, chill out!"
    end
  end

  class Unhelpfulness
    def to_s
      "Sure."
    end
  end

  class Sulkiness
    def to_s
      "Fine. Be that way."
    end
  end
end
