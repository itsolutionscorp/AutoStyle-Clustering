class Sentence
  def initialize(string)
    @sentence = string.strip unless string.nil?
  end

  def empty?
    @sentence.nil? || @sentence.empty?
  end

  def yelling?
    @sentence == @sentence.upcase
  end

  def asking?
    @sentence.end_with?('?')
  end
end

class Bob
  def hey(string)
    sentence = Sentence.new(string)
    case
    when sentence.empty? then
      'Fine. Be that way!'
    when sentence.yelling? then
      'Woah, chill out!'
    when sentence.asking? then
      'Sure.'
    else
      'Whatever.'
    end
  end
end

bob = Bob.new
