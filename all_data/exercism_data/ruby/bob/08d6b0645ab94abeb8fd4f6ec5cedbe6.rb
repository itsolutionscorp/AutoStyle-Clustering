class Bob
  def hey(message_of_critical_importance)
    message_interpreter.interpret(message_of_critical_importance, on_behalf_of: self)
  end

  def message_silent
    "Fine. Be that way."
  end

  def message_shouty
    "Woah, chill out!"
  end

  def message_interrogative
    "Sure."
  end

  def message_not_understood
    "Whatever."
  end

  private

  # Bob appears to be evaluating incoming communiques according to some
  # fairly crude criteria.  Since the field of psychology does not yet
  # appear to have a general solution for dependency injection
  # (methadone notwithstanding), we'll just leave this here for Bob to
  # replace as he matures...
  def message_interpreter
    TeenSpirit
  end
end



class TeenSpirit
  attr_reader :message

  def self.interpret(message, options = {})
    receiver = options.fetch(:on_behalf_of) { raise ArgumentError, "Must provide :on_behalf_of!" }
    new(message).interpret(receiver)
  end

  def initialize(message)
    @message = message
  end

  def interpret(receiver)
    mood = moods_grokked.detect {|mood| mood.applies_to?(message) }
    receiver.send(mood.handler)
  end

  def moods_grokked
    @moods_grokked ||=
      begin
        moods = [
          Moods::Silent,
          Moods::Shouty,
          Moods::Asky,
          Moods::NotUnderstood,
        ]
        moods.map(&:new)
      end
  end
end



module Moods
  class Shouty
    def applies_to?(message)
      message !~ /[a-z]/
    end

    def handler
      :message_shouty
    end
  end

  class Asky
    def applies_to?(message)
      message =~ /\?\Z/
    end

    def handler
      :message_interrogative
    end
  end

  class Silent
    def applies_to?(message)
      message.to_s.empty?
    end

    def handler
      :message_silent
    end
  end

  # Fallback
  class NotUnderstood
    def applies_to?(message)
      true
    end

    def handler
      :message_not_understood
    end
  end
end
