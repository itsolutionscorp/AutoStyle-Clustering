class Bob
  def hey(said)
    reply_to said
  end

  private

  def reply_to(said)
    bobspeak = BobSpeak.new said

    bobspeak.shouting?            and return "Woah, chill out!"
    bobspeak.asking?              and return "Sure."
    bobspeak.not_saying_anything? and return "Fine. Be that way!"

    "Whatever."
  end

  class BobSpeak
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
