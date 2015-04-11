class Bob
  def hey message=nil
    input = Input.new(message) 
    return 'Fine. Be that way!' if input.quietly_addressing?
    return 'Woah, chill out!' if input.shouting?
    return 'Sure.' if input.questioning?
    'Whatever.'
  end

  class Input
    attr_accessor :input

    def initialize(input)
      @input = input.to_s.strip
    end

    def only_upper_case_letters?
      input == input.upcase
    end
    alias_method :shouting?, :only_upper_case_letters?

    def questioning?
      input.end_with?('?')
    end

    def quietly_addressing?
      input.empty?
    end
  end
end
