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
  attr_reader :type, :verbal_communication
  
  def initialize(verbal_communication)
    @verbal_communication = verbal_communication
  end

  def type
    @type ||= lookup_type(verbal_communication)
  end

  def lookup_type(verbal_communication)
    type = { '!' => :exclamation,
             '?' => :question,
             '.' => :statement}[verbal_communication[-1]]
    type = :yelling if verbal_communication == verbal_communication.upcase
    type = :silence if verbal_communication == ''
    type
  end
end
