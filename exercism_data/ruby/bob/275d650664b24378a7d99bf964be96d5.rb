class Bob
  def hey statement
    statement ||= ''
    best_reponder_for(statement).response
  end

  private

  def best_reponder_for statement
    prioritized_responders.select do |responder|
      responder.appropriate_for? statement
    end.first
  end

  def prioritized_responders
    @_responders ||= [SilenceResponder.new,
                      ShoutResponder.new,
                      QuestionResponder.new,
                      DefaultResponder.new]
  end
end

class QuestionResponder
  def appropriate_for? statement
    statement[-1] == '?'
  end

  def response
    'Sure.'
  end
end

class ShoutResponder
  def appropriate_for? statement
    statement.upcase == statement
  end

  def response
    'Woah, chill out!'
  end
end

class SilenceResponder
  def appropriate_for? statement
    statement.strip.empty?
  end

  def response
    'Fine. Be that way!'
  end
end

class DefaultResponder
  def appropriate_for? statement
    true
  end

  def response
    'Whatever.'
  end
end
