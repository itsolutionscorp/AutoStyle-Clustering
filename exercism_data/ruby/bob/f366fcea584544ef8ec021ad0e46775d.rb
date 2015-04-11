# Thanks for your nitpicks tennety.  I've addressed each in comments below.

class Bob
  def hey(comment)
    # I had actually considered using Enumerable#detect at first, but I was put off by having to make Response#responds?
    # a class method.  However, I hadn't considered the ifnone parameter you can pass to #detect, and upon introspection
    # I realized this helps mitigate the fact that order matters in the Array in the previous version. Thank you!

    reply = [Responses::Silence, Responses::Shout, Responses::Question].detect(lambda { Responses::Default }) do |response|
      # I'm casting the comment to a string here instead of the nil check in the Response, good call.
      response.responds?(comment.to_s)
    end

    reply.new.text
  end
end


class Responses
  class Default
    def self.responds?(comment)
      true
    end

    def text
      "Whatever."
    end
  end

  class Question
    def self.responds?(comment)
      # I don't know why I didn't know about this method - maybe I did but just forgot.  Either way, thank you so much!
      comment.end_with?("?")
    end

    def text
      "Sure."
    end
  end

  class Shout
    def self.responds?(comment)
      comment == comment.upcase
    end

    def text
      "Woah, chill out!"
    end
  end

  class Silence
    def self.responds?(comment)
      comment.strip.empty?
    end

    def text
      "Fine. Be that way!"
    end
  end
end
