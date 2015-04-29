# A lackadaisical teenager. In conversation, his responses are very limited.
class Bob
  def hey(approach)
    case Approacher.new(approach) when :yells? then 'Woah, chill out!'
                                  when :asks_question? then 'Sure.'
                                  when :stays_silent? then 'Fine. Be that way.'
                                  else 'Whatever.'
    end
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
