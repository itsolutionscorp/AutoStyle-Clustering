class Bob
  def hey(talk_to_bob)
    respond_to( Phrase.new(talk_to_bob) )
  end

  def respond_to(phrase)
    { :default => 'Whatever.',
      :silence => 'Fine. Be that way.',
      :question => 'Sure.',
      :yelling => 'Woah, chill out!'
    }[phrase.type]
  end
end

class Phrase
  attr_reader :content
  
  def initialize(content)
    @content = content
  end

  def type
    @type ||= type_of_(content)
  end

  def type_of_(content)
    type = :default if content.end_with?('!', '.')
    type = :question if question(content)
    type = :yelling if yelling(content)
    type = :silence if silence(content)
    type
  end

  def question(content)
    content[-1] == '?'
  end

  def silence(content)
    content == ''
  end

  def yelling(content)
    content == content.upcase
  end
end
