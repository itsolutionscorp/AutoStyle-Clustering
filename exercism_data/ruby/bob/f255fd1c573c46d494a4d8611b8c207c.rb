class Bob
  def hey message=nil
    input = Input.new(message.to_s) 
    return 'Fine. Be that way!' if input.quietly_addressing?
    return 'Woah, chill out!' if input.shouting?
    return 'Sure.' if input.questioning?
    'Whatever.'
  end

  class Input < String
    def only_upper_case_letters?
      self == self.upcase
    end
    alias_method :shouting?, :only_upper_case_letters?

    def questioning?
      self.end_with?('?')
    end

    def quietly_addressing?
      self.nil? || self.empty? || only_whitespace?
    end

    def only_whitespace?
      self =~ /^\s+$/
    end
  end
end
