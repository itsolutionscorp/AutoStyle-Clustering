module Strategy
  class Whatever
    def answer
      'Whatever.'
    end
  end

  class Chill
    def apply?(string)
      string =~ /[A-Z]{4}|GO/
    end

    def answer
      'Woah, chill out!'
    end
  end

  class Sure
    def apply?(string)
      string =~ /\w\?\z/
    end

    def answer
      'Sure.'
    end
  end

  class Fine
    def apply?(string)
      string.strip.empty?
    end

    def answer
      'Fine. Be that way!'
    end
  end
end

class Bob
  def initialize(attrs = {})
    @strategies = []
    @defaut_strategy = attrs.fetch(:default_strategy, nil)
  end

  def hey(message)
    @strategies.reduce(@defaut_strategy) do |memo, strategy|
      memo = strategy if strategy.apply? message
      memo
    end.answer
  end

  def add_strategy(strategy)
    @strategies << strategy
  end
end
