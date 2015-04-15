class Bob

  def hey(msg)
    reply = replies.detect -> { base_reply } do |reply|
      reply.responds_to?(msg.to_s)
    end

    reply.new.response
  end


  private

  def base_reply
    Reply::Base
  end

  def replies
    [
      Reply::Quiet,
      Reply::Yelling,
      Reply::Question
    ]
  end

end

module Reply

  class Base

    def self.responds_to?(msg)
      true
    end

    def response
      "Whatever."
    end

  end


  class Yelling < Base

    def self.responds_to?(msg)
      msg == msg.upcase
    end

    def response
      "Woah, chill out!"
    end

  end


  class Question < Base

    def self.responds_to?(msg)
      msg.end_with?('?')
    end

    def response
      "Sure."
    end

  end


  class Quiet < Base

    def self.responds_to?(msg)
      msg.strip.empty?
    end

    def response
      "Fine. Be that way!"
    end

  end


end
