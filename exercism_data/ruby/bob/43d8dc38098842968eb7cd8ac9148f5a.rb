class Bob
  def hey(what_was_said)
    respond_to what_was_said
  end

  def respond_to(stimulus)
    responses = [
      SilenceResponse.new(stimulus),
      YellingResponse.new(stimulus),
      QuestionResponse.new(stimulus),
      DefaultResponse.new(stimulus)
    ]
    responses.detect(&:condition).response
  end
end

class DefaultResponse
  attr_reader :comment

  def initialize(comment)
    @comment = comment
  end

  def condition
    true
  end

  def response
    "Whatever."
  end
end

class SilenceResponse < DefaultResponse
  def condition
    comment.nil? || comment.empty?
  end

  def response
    "Fine. Be that way!"
  end
end

class YellingResponse < DefaultResponse
  def condition
    comment.upcase == comment
  end

  def response
    "Woah, chill out!"
  end
end

class QuestionResponse < DefaultResponse
  def condition
    comment.end_with? '?'
  end

  def response
    "Sure."
  end
end
