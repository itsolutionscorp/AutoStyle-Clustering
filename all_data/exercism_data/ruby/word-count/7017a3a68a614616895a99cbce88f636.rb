class Phrase

  def initialize(c)
    cadena = my_own_split(c)
    @phrase = cadena.each {|i| i.downcase!}
  end

  def my_own_split(cadena)
    cadena.split(/[^a-zA-Z0-9']/).delete_if { |word| word.empty? }
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

s = '*$foo *$bar *$bar *$baz'
p s
p Phrase.new(s).word_count
p s
