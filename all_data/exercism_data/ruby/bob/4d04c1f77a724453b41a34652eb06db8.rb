class Bob
  def hey(utterance)
    drivel = Drivel.new(utterance)
    return 'Fine. Be that way.' if drivel.silent?
    return 'Sure.' if drivel.question?
    return 'Woah, chill out!' if drivel.bawl?
    'Whatever.'
  end
end

class Drivel
  attr_reader :drivel

  def initialize(utterance)
    @drivel = utterance
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
