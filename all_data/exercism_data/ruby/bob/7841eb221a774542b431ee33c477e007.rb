class Bob
  attr_reader :utterance

  def hey(message)
    @utterance = message
    return 'Fine. Be that way.' if silent?
    return 'Sure.' if question?
    return 'Woah, chill out!' if bawl?
    return 'Whatever.' if statement?
  end

  private

  def statement?
    utterance.match(/(.|!)$/)
  end

  def bawl?
    utterance == utterance.upcase
  end

  def question?
    utterance.match(/\?$/)
  end

  def silent?
    utterance.nil? || utterance.empty?
  end
end
