class Bob
  def hey(statement)
    statement ||= ''
    best_response_for(statement).to_s
  end

  private

  def best_response_for(statement)
    prioritized_responses.select do |response|
      response.appropriate_for? statement
    end.first
  end

  def prioritized_responses
    @_responses ||= [SilenceResponse.new,
                      ShoutResponse.new,
                      QuestionResponse.new,
                      DefaultResponse.new]
  end
end

class QuestionResponse
  def appropriate_for?(statement)
    statement.end_with?('?')
  end

  def to_s
    'Sure.'
  end
end

class ShoutResponse
  def appropriate_for?(statement)
    statement.upcase == statement
  end

  def to_s
    'Woah, chill out!'
  end
end

class SilenceResponse
  def appropriate_for?(statement)
    statement.strip.empty?
  end

  def to_s
    'Fine. Be that way!'
  end
end

class DefaultResponse
  def appropriate_for?(statement)
    true
  end

  def to_s
    'Whatever.'
  end
end
