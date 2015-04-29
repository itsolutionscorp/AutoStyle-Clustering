class Bob
  def hey(message)
    HANDLERS.values.inject(nil) {|result, handler| result || handler.call(message) }
  end

  HANDLERS = {
    silence:         ->(message) { "Fine. Be that way!" if message.strip.empty? },
    shouting:        ->(message) { "Woah, chill out!" if message.upcase == message },
    questioning:     ->(message) { "Sure." if message.end_with?("?") },
    everything_else: ->(message) { "Whatever." },
  }
end
