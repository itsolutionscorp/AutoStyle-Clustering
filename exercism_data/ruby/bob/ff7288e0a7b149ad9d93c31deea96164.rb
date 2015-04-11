class Bob
  def hey(words)
    sentence = Sentence.new(words)

    if sentence.yelled?
      'Woah, chill out!'
    elsif sentence.question?
       'Sure.'
    elsif sentence.empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Sentence
  def initialize(words)
    @words = words
  end

  attr_reader :words

  def yelled?
    !empty? && words.upcase == words
  end

  def question?
    words.end_with?('?')
  end

  def empty?
    words.strip.size == 0
  end
end
