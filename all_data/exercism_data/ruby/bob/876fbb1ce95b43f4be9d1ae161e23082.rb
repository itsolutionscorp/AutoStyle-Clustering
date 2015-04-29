class Bob
  def hey(remark)
    Response.create(Remark.new(remark)).to_s
  end
end

module Response
  def self.create(remark)
    Responder.new(-> { remark.silence? && 'Fine. Be that way!' },
      Responder.new(-> { remark.shouting? && 'Woah, chill out!' },
        Responder.new(-> { remark.question? && 'Sure.' },
          "Whatever.")))
  end
end

class Remark
  def initialize(remark)
    @remark = remark
  end

  def silence?
    @remark.strip.empty?
  end

  def shouting?
    @remark.upcase == @remark
  end

  def question?
    @remark.end_with?("?")
  end
end


class Responder
  def initialize(evaluator, next_responder)
    @evaluator, @next_responder = evaluator, next_responder
  end

  def to_s
    @evaluator.call || @next_responder.to_s
  end
end
