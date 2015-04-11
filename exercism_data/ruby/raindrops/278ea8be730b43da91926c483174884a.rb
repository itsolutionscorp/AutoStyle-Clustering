class Raindrops
  def self.convert(input)
    responder = RaindropResponder.new(input)

    responder.respond
  end
end

class RaindropResponder
  def initialize(input)
    @input = input
  end

  def respond
    build_response.to_s || @input.to_s
  end

  private

  def build_response
    response = Response.new
    response << add_pling
    response << add_plang
    response << add_plong

    response
  end

  def add_pling
    'Pling' if is_factor?(3)
  end

  def add_plang
    'Plang' if is_factor?(5)
  end

  def add_plong
    'Plong' if is_factor?(7)
  end

  def is_factor?(factor)
    @input % factor == 0
  end
end

class Response
  def initialize
    @response = nil
  end

  def <<(string)
    if string
      @response ||= ''
      @response << string
    end
  end

  def to_s
    @response
  end
end
