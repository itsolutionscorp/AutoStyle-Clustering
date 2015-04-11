class Bob

  def hey(stuff_they_said)
    Rebuttal.new(IncomingCommunique.new(stuff_they_said)).call
  end

end

class Rebuttal
  attr_reader :communique

  RESPONSE_MAP = {
    :yelling => 'Woah, chill out!',
    :inquiring => 'Sure.',
    :silent => 'Fine. Be that way!',
    :lame => 'Whatever.'
  }

  def initialize(communique)
    @communique = communique
  end

  def call
    RESPONSE_MAP[communique.category]
  end

end

class IncomingCommunique
  attr_reader :content

  def initialize(content)
    @content = compress_multiline_content(content)
  end

  def category
    case
    when yelling? then :yelling
    when inquiring? then :inquiring
    when silent? then :silent
    else :lame
    end
  end

  #######
  private
  #######

  def yelling?
    content.match(/[A-Z]+/) and ! content.match(/[a-z]+/)
  end

  def inquiring?
    content.match(/\?$/)
  end

  def silent?
    content.match(/^[\s]*$/)
  end

  def compress_multiline_content(content)
    content.gsub(/[\r\n]/, '')
  end
end
