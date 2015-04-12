class Phrase

  attr_accessor :phrase

  def initialize(cadena)
    cadena.gsub!(/\s+/,' ')
    cadena.gsub!(/\!?\&?\@?\$?\%?\^?/,'')
    @phrase = cadena.split(/[\s\:,\n\t\.]/).delete_if {|word| word ==''}
    @phrase.each {|i| i.downcase!}
  end

  def word_count
    @phrase.each_with_object(Hash.new(0)) { |word,counts| counts[word] += 1}
  end
end
