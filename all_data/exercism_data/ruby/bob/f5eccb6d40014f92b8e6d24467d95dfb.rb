# A lackadaisical teenager. In conversation, his responses are very limited.
class Bob
  def hey(approach)
    approacher = Approacher.new(approach)
    return 'Woah, chill out!' if approacher.yells?
    return 'Sure.' if approacher.asks_question?
    return 'Fine. Be that way.' if approacher.stays_silent?
    'Whatever.'
  end
end

# Examine and categorize an approach text.
class Approacher
  def initialize(text)
    @text = text.to_s
  end

  def stays_silent?
    @text.empty?
  end

  def yells?
    @text == @text.upcase && !@text.empty?
  end

  def asks_question?
    @text.end_with?('?')
  end
end
