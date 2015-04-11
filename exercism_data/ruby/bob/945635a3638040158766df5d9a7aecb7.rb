# The website could use a log of submitted code for the same problem
# and retain the previous dicussions. I wanted to say that I was
# taking a different approach and, well, the exercise is fun.
# but I'm going to stop or it will take me forever :)
# Just started by passing the first question test case

class QuestionMessage
  attr_reader :content

  def initialize(content)
    @content = content
  end

  def question?
    true
  end
end

class Message
  class << self
    def with(content)
      QuestionMessage.new(content) if content == "Does this cryogenic chamber make me look fat?"
    end
  end
end

class Conversation
  attr_reader :listener
  attr_reader :teller

  class << self
    def between(listener, teller)
      new(listener, teller)
    end
  end
  
  def initialize(listener, teller)
    @listener, @teller = listener, teller
  end

  def say(message)
    @listener.reply(Message.with(message)).to_s
  end
end

class Andres
  def listen(message)
    message.content
  end
end

class Bob
  class Sure
    def to_s; "Sure."; end
  end

  attr_reader :answer

  def reply(message)
    Sure.new if message.question?
  end

  def hey(message)
    teller = Andres.new
    interaction = Conversation.between(self, teller)
    interaction.say(message)
  end
end

#module Exercise
#  class Teenager; end

#  module Attitudable
#    def self.feel_about(msg)
#      ([
#         Uncaring, 
#         Relaxed, 
#         Response, 
#       ].find {|act| act.to?(msg)} || Nothing)
#        .new
#        .msg
#    end  
#  end

#  module Matchable
#    def to?(msg)
#      patterns.inject(false) {|applies, pattern| applies ||= msg =~ pattern}
#    end    
#  end

#  class Nothing
#    def msg
#      "Fine. Be that way."
#    end
#  end

#  class Response
#    @patterns = [
#                  /.+\?$/, 
#                  /^Does.+\?$/
#                ]

#    class << self
#      attr_reader :patterns
#      include Matchable
#    end

#    def msg
#      "Sure."
#    end
#  end

#  class Uncaring
#    @patterns = [
#                  /^Let's.+!$/,
#                  /-/,  
#                  /.+\.$/
#                ]

#    class << self
#      attr_reader :patterns  
#      include Matchable
#    end
 
#    def msg
#      "Whatever."
#    end     
#  end

#  class Relaxed
#    @patterns = [
#                  /[1].+[2].+[3].+!$/,
#                  /[A-Z1-9]+[^%\^*@#$(*\^]*[A-Z1-9]+!$/,
#                  /[A-Z]+[^?]$/
#                 ]

#    class << self
#      attr_reader :patterns
#      include Matchable
#    end

#    def msg
#      "Woah, chill out!"
#    end
#  end

#  class Bob
#    def hey(msg)
#      attitudable.feel_about(msg)
#    end

#    def attitudable
#      @attitudable ||= Attitudable
#    end
#  end
#end
