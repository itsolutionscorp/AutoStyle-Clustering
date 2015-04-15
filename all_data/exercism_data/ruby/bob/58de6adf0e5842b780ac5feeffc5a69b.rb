class Bob
  def hey(parental_wisdom)
    wisdom = ParentalWisdom.new(parental_wisdom)

    return 'Fine. Be that way.' if wisdom.silence?
    return 'Woah, chill out!'   if wisdom.shouting?
    return 'Sure.'              if wisdom.question?

    'Whatever.'
  end

  class ParentalWisdom
    attr_reader :wisdom

    def initialize(wisdom)
      @wisdom = wisdom.to_s
    end

    def silence?
      wisdom.empty?
    end

    def shouting?
      wisdom.upcase == wisdom
    end

    def question?
      wisdom.end_with?('?')
    end
  end
end
