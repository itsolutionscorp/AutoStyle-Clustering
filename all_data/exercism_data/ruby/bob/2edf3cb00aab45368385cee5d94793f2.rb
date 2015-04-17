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
      # Does the text end with a question mark?
      text[-1] == "?"
    end

    def respond
      "Sure."
    end

  end

  class Shout

    def match?(text)
      # IS THE TEXT ALL CAPS?
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

class Bob
  include Responder

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
      Blank.new,
      Question.new,
      Shout.new,
      Statement.new,
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

end