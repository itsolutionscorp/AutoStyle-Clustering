class Bob

  def hey(msg)
    Response.new(msg).response
  end

  Response = Struct.new(:msg) do
    RESPONSES = {
      :dismissal     => "Fine. Be that way.",
      :calming    => "Woah, chill out!",
      :request      => "Sure.",
      :indifferent => "Whatever.",
    }

    def response
      RESPONSES.fetch(response_type) { no_response_found_error }
    end

    def no_response_found_error
      raise "Could not determine response for #{msg}"
    end

    def response_type
      if silence?
        :dismissal
      elsif shouting?
        :calming
      elsif asking?
        :request
      elsif stating? or demanding?
        :indifferent
      end
    end

    def shouting?
      msg.upcase == msg
    end

    def stating?
      msg[-1] == '.'
    end

    def asking?
      msg[-1] == '?'
    end

    def demanding?
      "Whatever."
    end

    def silence?
      msg.nil? || msg == ''
    end

  end

end
