class Bob
  attr_reader :utterance

  def hey(message)
    @utterance = message
    return 'Fine. Be that way.' if silent?
    return 'Sure.' if question?
    return 'Woah, chill out!' if bawl?
    'Whatever.'
  end

  private

  def bawl?
    utterance == utterance.upcase
  end

  def question?
    utterance.end_with?('?')
  end

  def silent?
    utterance.nil? || utterance.empty?
  end
end
