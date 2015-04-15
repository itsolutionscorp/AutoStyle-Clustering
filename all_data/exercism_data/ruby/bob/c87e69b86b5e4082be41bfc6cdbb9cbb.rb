class Bob
  def hey words
    words = Words.new(words)
    return 'Fine. Be that way!' if words.silence
    return 'Woah, chill out!' if words.yelling
    return 'Sure.' if words.question
    'Whatever.'
  end
end

class Words
  def initialize words
    @words = words || ''
  end

  def question
    @words.end_with?('?')
  end

  def yelling
    @words == @words.upcase
  end

  def silence
    @words.strip.empty?
  end
end
