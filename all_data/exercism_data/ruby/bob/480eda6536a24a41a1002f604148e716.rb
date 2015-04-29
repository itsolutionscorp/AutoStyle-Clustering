class Bob
  attr_reader :responses

  def initialize
    @emotional_responses = [Annoyed.new, TakenBack.new,
                  PassiveAggressive.new]
  end

  def hey(input=nil)
    @emotional_responses.each do |response|
      return response.to_s if response.produced_by?(input)
    end
    Indifferent.new.to_s
  end
end

class Response
  attr_reader :response

  def initialize
    return @response
  end

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
    return true if input.nil? || input.strip.empty?
    false
  end

end

class TakenBack < Response
  def initialize
    @response = 'Woah, chill out!'
  end

  def produced_by?(input)
    if !Annoyed.new.produced_by?(input) &&
      input.upcase == input
      return true
    end
    false
  end
end

class PassiveAggressive < Response
  def initialize
    @response = 'Sure.'
  end

  def produced_by?(input)
    if !TakenBack.new.produced_by?(input) &&
      input.end_with?('?')
      return true
    end
    false
  end
end

class Indifferent < Response
  def initialize
    @response = 'Whatever.'
  end
end
