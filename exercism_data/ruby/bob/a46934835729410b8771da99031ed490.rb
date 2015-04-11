class Bob
  def hey(greeting)
    respond_to ResponsibleAdult.new(greeting)
  end

  private

  def respond_to(adult)
    case
      when adult.is_silent?
        'Fine. Be that way!'
      when adult.is_yelling?
        'Woah, chill out!'
      when adult.is_asking_question?
        'Sure.'
      else
        'Whatever.'
    end
  end

  ResponsibleAdult = Struct.new(:greeting) do
    def is_silent?
      greeting.to_s.strip.empty?
    end

    def is_yelling?
      greeting == greeting.upcase
    end

    def is_asking_question?
      greeting.end_with? '?'
    end
  end
end
