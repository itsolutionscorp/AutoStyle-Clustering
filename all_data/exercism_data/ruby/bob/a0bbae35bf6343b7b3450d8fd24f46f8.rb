class Bob

  def hey(saying)
    @saying = saying

    if is_silent?
      'Fine. Be that way!'
    elsif is_shouting?
      'Woah, chill out!'
    elsif is_question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

    def is_shouting?
      all_caps || caps_with_exclamation
    end

    def all_caps
      @saying == @saying.upcase && !all_numbers
    end

    def caps_with_exclamation
      @saying =~ /[^a-z]\!$/
    end

    def is_question?
      @saying =~ /\?\z/m
    end

    def all_numbers
      @saying =~ /[^a-zA-Z\D]/
    end

    def is_silent?
      @saying.empty? || @saying !~ /\S/
    end
end
