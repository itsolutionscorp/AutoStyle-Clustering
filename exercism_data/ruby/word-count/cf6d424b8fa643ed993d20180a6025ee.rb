class Phrase

  NO_EVAL=/[\s+<,!&@$%^:\.]/
  attr_accessor :frase, :words

  def initialize(frase)
    @words = {}
    @frase = frase
  end

  def split_frase
    frase.split(NO_EVAL).each do |iteration|
      next if iteration == ""
      iteration.downcase!
      words[iteration] = words[iteration].to_i+1 
    end
  end

  def word_count
    split_frase if words.empty?
    words
  end
end
