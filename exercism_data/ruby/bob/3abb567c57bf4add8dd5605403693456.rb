class Bob

  def hey(message)
    answer_type = Answer.new(message).type
    answers.fetch(answer_type) { default_answer }
  end

  private

    def answers
      { nothing: "Fine. Be that way.",
        yell: "Woah, chill out!",
        question: "Sure."
      }
    end

    def default_answer
      "Whatever." 
    end

end

class Answer
  
  attr_reader :message
  def initialize(message)
    @message = message
  end

  def type
    case
    when nothing?
      :nothing
    when yell?
      :yell
    when question?
      :question
    else
      :unknown
    end
  end

  private

    def nothing?
      message.to_s.empty?
    end

    def question?
      message.end_with?("?")
    end

    def yell?
      message.to_s.upcase == message
    end

end
