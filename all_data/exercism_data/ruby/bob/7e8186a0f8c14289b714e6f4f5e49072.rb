class Bob
  RESPONSES = {
    silent:   'Fine. Be that way.',
    asking:   'Sure.',
    shouting: 'Woah, chill out!',
    default:  'Whatever.'
  }

  def hey(text)
    tone = ToneDeterminer.determine_tone(text)
    RESPONSES[tone]
  end
end

class ToneDeterminer
  class << self
    def determine_tone(text)
      new(text).tone
    end
  end

  ADJECTIVES = %w{silent shouting asking}
  attr_reader :text

  def initialize(text)
    @text = text
  end

  def tone
    ADJECTIVES.each do |adjective|
      return adjective.to_sym if send("#{adjective}?")
    end
    :default
  end

  private
    def silent? 
      text.to_s.empty?
    end

    def asking?
      text.end_with?("?")
    end

    def shouting?
      text.upcase == text
    end
end
