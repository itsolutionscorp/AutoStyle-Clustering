class Bob
  def hey(phrase)
    new_phrase = Phrase.new(phrase)
    respond_to[new_phrase.type]
  end

  def respond_to
    { :statement => 'Whatever.',
      :exclamation =>  'Whatever.',
      :silence => 'Fine. Be that way.',
      :question => 'Sure.',
      :yelling => 'Woah, chill out!'
    }
  end
end

class Phrase
  attr_reader :type, :phrase

  def initialize(phrase)
    @phrase = phrase
    @type = get_phrase_type(phrase)
  end

  def get_phrase_type(phrase)
    type = lookup_type[phrase[-1]]
    type = :yelling if phrase == phrase.upcase
    type = :silence if phrase == ''
    type
  end

  def lookup_type
    {'!' => :exclamation, '?' => :question, '.' => :statement}
  end
end
