class Bob
  def hey(talk_to_bob)
    respond_to( Phrase.new(talk_to_bob) )
  end

  def respond_to(phrase)
    { :statement => 'Whatever.',
      :exclamation =>  'Whatever.',
      :silence => 'Fine. Be that way.',
      :question => 'Sure.',
      :yelling => 'Woah, chill out!'
    }[phrase.type]
  end
end

class Phrase
  attr_reader :type, :talk_to_bob
  
  def initialize(talk_to_bob)
    @talk_to_bob = talk_to_bob
  end

  def type
    @type ||= lookup_type(talk_to_bob)
  end

  def lookup_type(talk_to_bob)
    type = { '!' => :exclamation,
             '?' => :question,
             '.' => :statement}[talk_to_bob[-1]]
    type = :yelling if talk_to_bob == talk_to_bob.upcase
    type = :silence if talk_to_bob == ''
    type
  end
end
