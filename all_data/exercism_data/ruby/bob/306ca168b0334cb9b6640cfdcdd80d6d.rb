class Bob
  def hey(comment)
    reply = possible_responses.detect(lambda { default_response }) do |response|
      response.replies?(comment.to_s)
    end

    reply.new.reply
  end

  private

  def possible_responses
    [Responses::Silence, Responses::Shout, Responses::Question]
  end

  def default_response
    Responses::Default
  end
end


module Responses
  class Default
    def self.replies?(comment)
      true
    end

    def reply
      "Whatever."
    end
  end

  class Question
    def self.replies?(comment)
      comment.end_with?("?")
    end

    def reply
      "Sure."
    end
  end

  class Shout
    def self.replies?(comment)
      comment == comment.upcase
    end

    def reply
      "Woah, chill out!"
    end
  end

  class Silence
    def self.replies?(comment)
      comment.strip.empty?
    end

    def reply
      "Fine. Be that way!"
    end
  end
end
