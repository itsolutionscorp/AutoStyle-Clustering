class Bob
  def hey(parental_wisdom)
    wisdom = ParentalWisdom.new(parental_wisdom)

    return 'Fine. Be that way.' if wisdom.silence?
    return 'Woah, chill out!'   if wisdom.shouting?
    return 'Sure.'              if wisdom.question?

    'Whatever.'
  end

  ParentalWisdom = Struct.new(:wisdom) do
    def silence?
      wisdom.nil? || wisdom.empty?
    end

    def shouting?
      wisdom.upcase == wisdom
    end

    def question?
      wisdom.end_with?('?')
    end
  end
end
