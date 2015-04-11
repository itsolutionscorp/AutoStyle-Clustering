class Bob
  def hey(greeting)
    adult = ResponsibleAdult.new(greeting)

    if adult.is_silent?
      'Fine. Be that way!'
    elsif adult.is_yelling?
      'Woah, chill out!'
    elsif adult.is_asking_question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  ResponsibleAdult = Struct.new(:greeting) do
    def is_silent?
      greeting.to_s =~ /^\s*$/
    end

    def is_yelling?
      greeting =~ /^[^a-z]+$/
    end

    def is_asking_question?
      greeting =~ /\?$/
    end
  end
end
