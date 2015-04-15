class Bob
  INTERPRETATIONS = {
    silent: :nothing_to_say_to_me,
    loud: :yelling_at_me,
    question: :want_something_from_me,
    statement: :imparting_wisdom_at_me
  }

  RESPONSES = {
    nothing_to_say_to_me: "Fine. Be that way!",
    yelling_at_me: "Woah, chill out!",
    want_something_from_me: "Sure.",
    imparting_wisdom_at_me: "Whatever."
  }

  def hey(mouthnoises)
    utterance = Utterance.new(mouthnoises.to_s)
    subjective_interpretation = interpret(utterance)
    respond_to(subjective_interpretation)
  end

  private

  def interpret(utterance)
    INTERPRETATIONS[utterance.parse]
  end

  def respond_to(interpretation)
    RESPONSES[interpretation]
  end
end

class Utterance < String
  def parse
    silent?   ? :silent    :
    loud?     ? :loud      :
    question? ? :question  :
                :statement
  end

  private

  def silent?
    empty?
  end

  def loud?
    upcase == self
  end

  def question?
    chars.last == "?"
  end
end
