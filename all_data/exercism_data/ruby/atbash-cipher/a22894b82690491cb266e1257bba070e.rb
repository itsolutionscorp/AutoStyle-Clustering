class Atbash
  def self.encode phrase
    Atbash.new(phrase).chunked(5)
  end

  PLAIN_LIST = ('a'..'z').to_a.join
  CYPHER_LIST = PLAIN_LIST.reverse
  
  attr_reader :phrase
  def initialize phrase
    @phrase = phrase.to_s.downcase
  end

  def translate
    @translated ||= phrase.tr(PLAIN_LIST, CYPHER_LIST)
  end
  
  def chunked length = 5
    clean.chars.each_slice(length).to_a.map(&:join).join(" ")
  end
  
  private
  def clean
    translate.gsub(/[^\w]/, '')
  end
end
