# Updated after completion to be like example 4, which I quite liked.
class Bob

  Handler = Struct.new(:response, :pattern)

  HANDLERS = {
    :nothing    => Handler.new("Fine. Be that way!",  ->(i) { i.strip.empty? }),
    :yell       => Handler.new("Woah, chill out!",    ->(i) { i.eql?(i.upcase) }),
    :question   => Handler.new("Sure.",               ->(i) { i.end_with?("?") }),
    :statement  => Handler.new("Whatever.",           ->(i) { true })
  }

  def hey(drivel)
    respond(select_handler(drivel.to_s))
  end

  private

  def respond(handler)
    handler.response
  end

  def select_handler(drivel)
    handlers.values.find { |r| r.pattern.call(drivel) }
  end

  def handlers
    HANDLERS
  end

end
