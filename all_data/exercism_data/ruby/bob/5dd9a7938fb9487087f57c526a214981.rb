class Bob
  def hey(phrase)
    new_phrase = Phrase.new(phrase)
    respond_to(new_phrase.type)
  end

  def respond_to(type)
    { :statement => 'Whatever.',
      :exclamation =>  'Whatever.',
      :silence => 'Fine. Be that way.',
      :question => 'Sure.',
      :yelling => 'Woah, chill out!'
    }[type]
  end
end

class Phrase
  attr_reader :type, :phrase

  def initialize(phrase)
    @phrase = phrase
    @type ||= lookup_type(phrase)
  end

  def lookup_type(phrase)
    type = { '!' => :exclamation,
             '?' => :question,
             '.' => :statement}[phrase[-1]]
    type = :yelling if phrase == phrase.upcase
    type = :silence if phrase == ''
    type
  end
end
