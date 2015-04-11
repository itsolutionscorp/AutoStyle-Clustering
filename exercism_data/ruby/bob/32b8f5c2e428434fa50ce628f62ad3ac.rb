class Bob
  class Phrase < Struct.new :format, :response
    SILENCE  = new /\A\s*\Z/,     'Fine. Be that way!'
    SHOUTING = new /\A[^a-z]+\Z/, 'Woah, chill out!'
    QUESTION = new /\?\Z/,        'Sure.'
    ANYTHING = new //,            'Whatever.'

    ALL = [SILENCE, SHOUTING, QUESTION, ANYTHING]
  end

  def hey(text)
    Phrase::ALL.find {|it| it.format =~ text }.response
  end
end
