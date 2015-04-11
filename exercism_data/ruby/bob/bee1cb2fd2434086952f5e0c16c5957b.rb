class Bob
  RESPONSES = {
    :silent     => 'Fine. Be that way.',
    :question   => 'Sure.',
    :bawl       => 'Woah, chill out!',
    :statement  => 'Whatever.'
  }

  def hey(utterance)
    drivel = Drivel.new(utterance)
    RESPONSES[drivel.type]
  end
end

class Drivel
  attr_reader :drivel

  def initialize(utterance)
    @drivel = utterance
  end

  def type
    return :silent if silent?
    return :question if question?
    return :bawl if bawl?
    :statement
  end

  def bawl?
    drivel == drivel.upcase
  end

  def question?
    drivel.end_with?('?')
  end

  def silent?
    drivel.nil? || drivel.empty?
  end
end
