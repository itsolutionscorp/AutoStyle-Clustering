class Bob

  def initialize
    @responders = [
      Responder::Empty.new,
      Responder::Question.new,
      Responder::Shout.new,
      Responder::Statement.new,
    ]
  end

  def find_responder(text)
    @responders.find{|responder| responder.match?(text)}
  end

  def hey(text)
    find_responder(text).respond
  end

  module Responder

    class Empty

      def match?(text)
        text.nil? || text.empty?
      end

      def respond
        "Fine. Be that way."
      end

    end

    class Question

      def match?(text)
        text[-1] == "?"
      end

      def respond
        "Sure."
      end

    end

    class Shout

      def match?(text)
        text.upcase == text
      end

      def respond
        "Woah, chill out!"
      end

    end

    class Statement

      def match?(text)
        true
      end

      def respond
        "Whatever."
      end

    end

  end

end
