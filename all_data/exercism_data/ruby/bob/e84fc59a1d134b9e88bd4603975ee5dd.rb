class Bob
  def hey(said)
    Responder.new(said).response
  end
end

class Responder
  RESPONSES = {
      nothing: "Fine. Be that way!",
      yelled: "Woah, chill out!",
      question: "Sure.",
      other: "Whatever."
    }

  def initialize(said)
    @said = said
  end

  def response
    RESPONSES[response_type]
  end

  private

  def response_type
    if is_said_nothing?
      :nothing
    elsif is_being_yelled_at?
      :yelled
    elsif is_asked_question?
      :question
    else
      :other
    end
  end

  def is_being_yelled_at?
    @said == @said.upcase && @said.upcase != @said.downcase
  end

  def is_said_nothing?
    @said.strip == ""
  end

  def is_asked_question?
    @said.end_with? "?"
  end
end
