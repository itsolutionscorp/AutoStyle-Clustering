class Bob
  def hey(talk)
    Reaction.choose_response(talk)
  end
end

class Reaction
  def self.choose_response(talk)
    [ Silence.new,
      Yelling.new,
      Question.new,
      Statement.new
    ].find{ |reply| reply.match?(talk) }.execute
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
