class Bob
  attr_reader :responses

  def initialize
    @responses = [Annoyed.new, TakenAback.new,
                  PassiveAggressive.new, Indifferent.new]
  end

  def hey(input=nil)
    @responses.find {|response| response.produced_by?(input) }.to_s
  end
end

class Response

  def to_s
    @response
  end

  def produced_by?(input)
    return true
  end

end

class Annoyed < Response
  def initialize
    @response = 'Fine. Be that way!'
  end

  def produced_by?(input)
    input.nil? || input.strip.empty?
  end

end

class TakenAback < Response
  def initialize
    @response = 'Woah, chill out!'
  end

  def produced_by?(input)
    input.upcase == input
  end
end

class PassiveAggressive < Response
  def initialize
    @response = 'Sure.'
  end

  def produced_by?(input)
    input.end_with?('?')
  end
end

class Indifferent < Response
  def initialize
    @response = 'Whatever.'
  end

  def produced_by?(input)
    return true
  end
end
