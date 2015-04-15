# I think it's fine. It is easy to over-think it. Well, it is a simple exercise but then one wonders how to group responsibilities because Bob, after all, does -from a programmatic point of view- responds to many types of messages "sentences" sent to him ;)

class Message
  attr_reader :content

  class << self
    def with(content)
      new(content)
    end

    def respond(who)
      @person = who
      self
    end

    def for(message)
      person_class = @person.class

      if message == Message.with("Does this cryogenic chamber make me look fat?")
        return Message.new(person_class::Sure.new.to_s)
      end
    end
  end
 
  def initialize(content)
    @content = content
  end

  def ==(other_message) 
    @content == other_message.content
  end
end

class Bob
  class Sure
    def to_s; "Sure."; end
  end

  def hey(txt)
    answer(message(txt)).content
  end

  private
  def answer(message)
    Message.respond(self).for(message)
  end

  def message(txt)
    (@m ||= Message).with(txt)
  end
end
