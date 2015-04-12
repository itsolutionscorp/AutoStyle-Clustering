class Phrase
  attr_reader :word_hash
  alias :word_count :word_hash

  def initialize phrase
    @words = parse phrase
    create_hash
  end

  def create_hash
    @word_hash = {}
    @word_hash.default = 0
    @words.each {|k,v|
      @word_hash[k] += 1
    }
  end
  private :create_hash

  def parse phrase
    words = phrase.downcase.gsub(',',' ').split.each {|e| e.gsub!(/\W/,'') }
    words.delete ''
    words
  end
  private :parse
end
