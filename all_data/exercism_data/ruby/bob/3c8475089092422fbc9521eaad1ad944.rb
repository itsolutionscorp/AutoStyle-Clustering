class Bob
  def hey(response)
    r = Reaction.new(response)
    r.say
  end
end

class Reaction
  def initialize(input)
    @response = input
  end

  def say
    choose_response(@response)
  end

  def choose_response(input)
    [ Silence.new,
      Yelling.new,
      Question.new,
      Statement.new
    ].find{ |reply| reply.match?(input) }.execute
  end
end

class Silence
  def match?(input)
    input.strip.empty?
  end

  def execute
    'Fine. Be that way!'
  end
end

class Yelling
  def match?(input)
    input.upcase == input
  end

  def execute
    'Woah, chill out!'
  end
end

class Question
  def match?(input)
    input.end_with?('?')
  end

  def execute
    'Sure.'
  end
end

class Statement
  def match?(input)
    true
  end

  def execute
    'Whatever.'
  end
end
