class Phrase

  attr_accessor :phrase

  def initialize(phrase)
    self.phrase = phrase
  end

  def word_count
    phrase.scan(/\w+/).inject(Hash.new(0)) {|s,v| v.downcase!; s.update v => s[v].next}
  end

end
