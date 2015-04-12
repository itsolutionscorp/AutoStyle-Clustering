class Phrase

  def initialize(c)
    cadena = clean(c)
    cadena = split(cadena)  
    @phrase = cadena.each {|i| i.downcase!}
  end

  def clean(cadena)
    cadena.gsub!(/\s+/,' ')
    cadena.gsub!(/[^a-zA-Z0-9\s][!&@$%^]/,'')
    cadena
  end

  def split(cadena)
    cadena.split(/[\s,:\.]/).delete_if {|word| word == ''}
  end

  def word_count
    bits = @phrase
    bits.each_with_object(Hash.new(0)) { |word,counts| counts[word] += 1}
  end
end

s = 'foo bar bar baz'
p s
p Phrase.new(s).word_count
p s


s = '$foo $bar $bar $baz'
p s
p Phrase.new(s).word_count
p s
