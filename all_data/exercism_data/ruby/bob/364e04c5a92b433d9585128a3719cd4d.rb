class Bob

  Behavior = Struct.new(:pattern, :response) do
    def match?(text)
      text =~ pattern
    end
  end

  def self.respond(text, options = {})
    Behavior.new(options.fetch(:to) { // }, text)
  end

  BEHAVIORS = [
    respond("Fine. Be that way!", to: /\A\s*\Z/    ),
    respond("Sure.",              to: /[a-z].*\?\Z/),
    respond("Whatever.",          to: /[a-z]/      ),
    respond("Woah, chill out!"                     ),
  ]

  def hey(message)
    BEHAVIORS.detect { |b| b.match? message }.response
  end

end
