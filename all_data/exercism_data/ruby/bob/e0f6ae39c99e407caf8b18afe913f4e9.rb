class Bob < Struct.new(nil)

  def hey(input)
    which_response(input).answer
  end

  def answers
    shouting    = Shouting.new
    questioning = Questioning.new
    no_input    = NoInput.new
    whatevering = Whatevering.new
    [shouting, questioning, no_input, whatevering]
  end

  def which_response(input)
    answers.find { |answer| answer.match?(input) }
  end

  class Shouting
    def match?(input)
      input == input.upcase && input.match(/[a-zA-Z]/)
    end

    def answer
      'Woah, chill out!'
    end
  end

  class Questioning
    def match?(input)
      input.match(/\?\z/)
    end

    def answer
      'Sure.'
    end
  end

  class NoInput
    def match?(input)
      input.strip.empty?
    end

    def answer
      'Fine. Be that way!'
    end
  end

  class Whatevering
    def match?(input)
      true
    end

    def answer
      'Whatever.'
    end
  end
end
