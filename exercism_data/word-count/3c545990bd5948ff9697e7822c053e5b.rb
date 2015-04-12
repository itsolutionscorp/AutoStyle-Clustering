class Phrase

  def initialize(c)
    cadena = my_own_split(c)
    @phrase = cadena.each {|i| i.downcase!}
  end

  def my_own_split(cadena)
    cadena.split(/[^a-zA-Z0-9']/).delete_if(&:empty?)
  end

  def word_count
    @phrase.each_with_object(Hash.new(0)) { |word,counts| counts[word] += 1}
  end
end
