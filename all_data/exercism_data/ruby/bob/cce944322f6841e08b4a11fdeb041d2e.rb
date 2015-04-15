class Bob
  MAPPINGS = [
    { condition: :empty?,    answer: 'Fine. Be that way!' },
    { condition: :shouting?, answer: 'Whoa, chill out!' },
    { condition: :question?, answer: 'Sure.' },
    { condition: :other?,    answer: 'Whatever.'}
  ]

  def hey(text)
    sentence = Sentence.new(text)
    MAPPINGS.find { |m| sentence.public_send(m[:condition]) }[:answer]
  end
end

class Sentence < String
  def initialize(text)
    super(text.strip)
  end

  def question?
    self[-1] == '?'
  end

  def shouting?
    self =~ /[A-Z]/ && !(self =~ /[a-z]/)
  end

  def other?
    not(empty? || question? || shouting?)
  end
end
