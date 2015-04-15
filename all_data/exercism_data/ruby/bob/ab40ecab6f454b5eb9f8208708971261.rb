class Bob

  def initialize
    # The order of the responders is significant. That's why this is an array.
    # The first responder (Responder::Blank) checks if the text is nil.
    # Subsequent responders assume the text is a non-nil string.
    # The order of the next two responders (Responder::Shout and
    # Responder::Question) is only relevant to shouted questions (e.g. "WTF?"),
    # which both match. The correct behavior for this case is not specified.
    # The final responder (Responder::Statement) is a catch-all. If the text is
    # not blank, a question, or a shout, it is a statement (by default).
    @responders = [
      Responder::Blank.new,
      Responder::Question.new,
      Responder::Shout.new,
      Responder::Statement.new,
    ]
  end

  def find_responder(text)
    # Return the first responder that matches the text
    @responders.find do |responder|
      responder.match?(text)
    end
  end

  def hey(text)
    find_responder(text).respond
  end

  # A Responder implements two instance methods: match? and respond
  module Responder

    class Blank

      def match?(text)
        text.nil? || text.empty?
      end

      def respond
        "Fine. Be that way."
      end

    end

    class Question

      def match?(text)
        text.end_with?("?")
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
