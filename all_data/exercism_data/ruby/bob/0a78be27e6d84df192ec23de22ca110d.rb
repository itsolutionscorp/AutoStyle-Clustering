class Bob
  def hey(said)
    reply_to said
  end

  private

  def reply_to(said)
    message = BobSpeakMessage.new said

    return "Woah, chill out!"    if message.shouting?
    return "Sure."               if message.asking?
    return "Fine. Be that way!"  if message.not_saying_anything?

    "Whatever."
  end

  class BobSpeakMessage
    attr_reader :spoken

    def initialize(spoken)
      @spoken = spoken
    end

    def shouting?
      has_upper, has_lower = spoken.match(/[[:upper:]]+/), spoken.match(/[[:lower:]]+/)

      has_upper && !has_lower
    end

    def asking?
      spoken.end_with? '?'
    end

    def not_saying_anything?
      spoken.strip.empty?
    end
  end
end
