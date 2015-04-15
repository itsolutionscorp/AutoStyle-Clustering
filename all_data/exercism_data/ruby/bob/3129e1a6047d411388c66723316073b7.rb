class Bob
  def hey message=nil
    input = Input.new(message.to_s) 
    return 'Fine. Be that way!' if input.is_quietly_addressing?
    return 'Woah, chill out!' if input.is_shouting?
    return 'Sure.' if input.is_questioning?
    'Whatever.'
  end

  class Input < String
    def has_only_upper_case_letters?
      self == self.upcase
    end
    alias_method :is_shouting?, :has_only_upper_case_letters?

    def is_questioning?
      self.end_with?('?')
    end

    def is_quietly_addressing?
      self.nil? || self.empty? || has_only_whitespace?
    end

    def has_only_whitespace?
      self =~ /^\s+$/
    end
  end
end
