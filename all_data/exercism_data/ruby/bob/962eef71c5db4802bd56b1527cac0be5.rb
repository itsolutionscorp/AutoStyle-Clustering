class Bob
  def hey(input)
    Answer.new(input).answer
  end
end

class Answer
  def initialize(input)
    @input = input.to_s
  end

  def answer
    return 'Fine. Be that way!' if silence?
    return 'Woah, chill out!' if yell?
    return 'Sure.' if question?
    'Whatever.'
  end

  private

  def question?
    @input.end_with?('?') and not yell?
  end

  def yell?
    @input.upcase == @input and not silence?
  end

  def silence?
    @input == ''
  end
end
