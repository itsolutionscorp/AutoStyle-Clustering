class Bob
    def hey(something)
        BobResponder.new(something).respond
    end
end

class BobResponder
  def initialize(something)
    @something = something
  end

  def respond
    return case
    when is_silence?
        "Fine. Be that way!"
    when is_uppercase?
         'Woah, chill out!'
    when is_question?
        "Sure."
    else
        "Whatever."
    end
  end

  private

  def is_silence?
      blank?
  end

  def is_question?
    @something.end_with?('?')
  end

  def is_uppercase?
    @something.upcase == @something
  end

  def blank?
    @something.nil? || @something == ""
  end
end
