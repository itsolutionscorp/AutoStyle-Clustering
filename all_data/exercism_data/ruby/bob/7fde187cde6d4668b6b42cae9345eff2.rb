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
    RESPONSE_MAP.keys.each do |category|
      return RESPONSE_MAP[category] if communique.send("#{category}?")
    end
  end

end

class IncomingCommunique
  attr_reader :content

  def initialize(content)
    @content = compress_multiline_content(content)
  end

  def yelling?
    content.match(/[A-Z]+/) and ! content.match(/[a-z]+/)
  end

  def inquiring?
    content.match(/\?$/)
  end

  def silent?
    content.match(/^[\s]*$/)
  end

  def lame?
    true
  end

  #######
  private
  #######

  def compress_multiline_content(content)
    content.gsub(/[\r\n]/, '')
  end
end
