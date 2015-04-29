class Bob
  def hey(comment)
    [Responses::Silence, Responses::Shout, Responses::Question, Responses::Default].each do |response|
      reply = response.new(comment)
      return reply.text if reply.responds?
    end
  end
end


class Responses
  class Response
    def initialize(comment)
      @comment = comment || ''
    end
  end

  class Default < Response
    def responds?
      true
    end

    def text
      "Whatever."
    end
  end

  class Question < Response
    def responds?
      @comment =~ /\?$/ 
    end

    def text
      "Sure."
    end
  end

  class Shout < Response
    def responds?
      @comment == @comment.upcase 
    end

    def text
      "Woah, chill out!"
    end
  end

  class Silence < Response
    def responds?
      @comment.strip.empty?
    end

    def text
      "Fine. Be that way!"
    end
  end
end
