class Phrase

  attr_accessor :phrase

  def initialize(cadena)
    clean(cadena)
    split(cadena)  
    @phrase.each {|i| i.downcase!}
  end

  def clean(cadena)
    cadena.gsub!(/\s+/,' ')
    cadena.gsub!(/[!&@$%^]/,'')
  end

  def split(cadena)
    @phrase = cadena.split(/[\s,:\.]/).delete_if {|word| word == ''}
  end

  def word_count
    @phrase.each_with_object(Hash.new(0)) { |word,counts| counts[word] += 1}
  end
end
