# A lackadaisical teenager. In conversation, his responses are very limited.
class Bob
  def hey(approach)
    approacher = Approacher.new(approach)
    return 'Sure.' if approacher.asks_question?
    return 'Whatever.' if approacher.tells_something?
    return 'Woah, chill out!' if approacher.yells?
    return 'Fine. Be that way.' if approacher.doesnt_say_anything?
  end
end

# Examine and categorize an approach text.
class Approacher
  def initialize(text)
    @text = text.to_s
  end

  def doesnt_say_anything?
    @text.empty?
  end

  def yells?
    @text == @text.upcase && !doesnt_say_anything?
  end

  def asks_question?
    @text.end_with?('?') && !yells?
  end

  def tells_something?
    !yells? && !doesnt_say_anything? && !asks_question?
  end
end
