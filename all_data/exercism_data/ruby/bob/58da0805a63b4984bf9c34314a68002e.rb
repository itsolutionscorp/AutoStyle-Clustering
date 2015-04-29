class Bob
  MAPPINGS = [
    { condition: :empty?,    answer: 'Fine. Be that way!' },
    { condition: :shouting?, answer: 'Woah, chill out!' },
    { condition: :question?, answer: 'Sure.' },
    { condition: :other?,    answer: 'Whatever.'}
  ]

  def hey(text)
    sentence = Sentence.new(text)
    MAPPINGS.each do |mapping|
      return mapping[:answer] if sentence.public_send(mapping[:condition])
    end
  end
end

class Sentence
  def initialize(text)
    @text = text.strip
  end

  def empty?
    text.empty?
  end

  def question?
    text[-1] == '?'
  end

  def shouting?
    text =~ /[A-Z]/ && !(text =~ /[a-z]/)
  end

  def other?
    not(empty? || question? || shouting?)
  end

  private

  attr_reader :text
end
