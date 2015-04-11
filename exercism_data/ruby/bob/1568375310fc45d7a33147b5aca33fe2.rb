class Bob
  def hey(remark)
    Response.create(remark).to_s
  end
end

class Response
  def self.create(remark)
    Responder.new(-> { remark.strip.empty? && 'Fine. Be that way!' },
      Responder.new(-> { remark.upcase == remark && 'Woah, chill out!' },
        Responder.new(-> { remark.end_with?("?") && 'Sure.' })))
  end
end

class Responder
  def initialize(evaluator, next_responder = "Whatever.")
    @evaluator, @next_responder = evaluator, next_responder
  end

  def to_s
    @evaluator.call || @next_responder.to_s
  end
end
