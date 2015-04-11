class Bob
  def hey(remark)
    Response.create(remark).to_s
  end
end

class Response
  def self.create(remark)
    responses = [
      [ ->(remark) { remark.strip.empty? },     'Fine. Be that way!' ],
      [ ->(remark) { remark.upcase == remark }, 'Woah, chill out!' ],
      [ ->(remark) { remark[-1] == "?" },       'Sure.' ],
      [ ->(_)      { true },                    'Whatever.' ]
    ]
    responder = responses.find { |fn, response| fn.call(remark) }.last
    new(responder)
  end

  def initialize(response)
    @response = response
  end

  def to_s
    @response
  end
end
