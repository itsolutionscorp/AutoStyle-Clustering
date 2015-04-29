#!/usr/bin/env ruby

class String
  def allcaps?
    self == self.upcase
  end
end

class Bob

  def hey you
    return Bob.fine if you.empty? or you =~ /\A\s+\z/
    return Bob.chillout if you.allcaps?
    return Bob.sure if you.end_with? '?'
    Bob.whatever
  end

  private

  class << self
    def fine
      'Fine. Be that way!'
    end

    def sure
      "Sure."
    end

    def chillout
      "Woah, chill out!"
    end

    def whatever
      "Whatever."
    end
  end

  # Above is what I believe the story is implying about
  # implementation. Below would be how I'd implement in
  # production.
  RESPONSES = {
    'WATCH OUT!'                                    => -> { Bob.chillout },
    'I HATE YOU'                                    => -> { Bob.chillout },
    'WHAT THE HELL WERE YOU THINKING?'              => -> { Bob.chillout },
    '1, 2, 3 GO!'                                   => -> { Bob.chillout },
    'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!' => -> { Bob.chillout },
    'You are, what, like 15?'                       => -> { Bob.sure },
    'Does this cryogenic chamber make me look fat?' => -> { Bob.sure },
    'Wait! Hang on. Are you going to be OK?'        => -> { Bob.sure },
  }

  public

  def hey_alt you
    return Bob.fine if you.empty? or you =~ /\A\s+\z/
    RESPONSES[you] ? RESPONSES[you].call : Bob.whatever
  end
end
