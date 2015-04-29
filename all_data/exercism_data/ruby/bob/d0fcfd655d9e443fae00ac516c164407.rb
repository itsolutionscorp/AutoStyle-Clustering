class Sentence
  def initialize(words)
    @words = words
  end

  def shouted?
    includes_letter? && upper_case?
  end

  def question?
    words.end_with? '?'
  end

  def silent?
    words.strip.empty?
  end

  private

  attr_reader :words

  def includes_letter?
    words =~ /[A-Z]/i
  end

  def upper_case?
    words == words.upcase
  end
end

class Bob
  def hey(words)
    reply_to(Sentence.new(words))
  end

  private

  def reply_to(sentence)
    if sentence.shouted?
      'Woah, chill out!'
    elsif sentence.question?
      'Sure.'
    elsif sentence.silent?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
