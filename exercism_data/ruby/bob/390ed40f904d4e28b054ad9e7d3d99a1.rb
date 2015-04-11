# A lackadaisical teenager. In conversation, his responses are very limited.
class Bob
  def hey(approach)
    approacher = Approacher.new(approach)
    return 'Sure.' if approacher.asks_question?
    return 'Woah, chill out!' if approacher.yells?
    return 'Fine. Be that way.' if !approacher.says_something?
    'Whatever.'
  end
end

# Examine and categorize an approach text.
class Approacher
  def initialize(text)
    @text = text.to_s
  end

  def says_something?
    !@text.empty?
  end

  def yells?
    @text == @text.upcase && says_something?
  end

  def asks_question?
    @text.end_with?('?') && !yells?
  end
end
