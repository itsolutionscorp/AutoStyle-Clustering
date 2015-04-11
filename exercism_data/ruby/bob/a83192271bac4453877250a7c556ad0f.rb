class Bob

  def hey(response)
    r = Reaction.new(response)
    r.say
  end

  class Reaction

    def initialize(input)
      @response = choose_response(input)
    end

    def choose_response(input)
      replies = [Silence.new, Yelling.new, Question.new, Statement.new]
      found_reply = replies.find{ |reply| reply.match?(input) }
      found_reply.execute
    end

    def say
      @response
    end

    class Silence
      def match?(input)
        input.strip.empty?
      end
      def execute
        'Fine. Be that way!'
      end
    end

    class Yelling
      def match?(input)
        input.upcase == input
      end
      def execute
        'Woah, chill out!'
      end
    end

    class Question
      def match?(input)
        input.end_with?("?")
      end
      def execute
        'Sure.'
      end
    end

    class Statement
      def match?(input)
        true
      end
      def execute
        'Whatever.'
      end
    end
  end
end
