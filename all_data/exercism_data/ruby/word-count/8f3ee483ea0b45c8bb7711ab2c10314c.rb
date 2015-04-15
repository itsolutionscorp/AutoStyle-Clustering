class Phrase
  def initialize str
    String === str or raise "argument to Phrase#new must be a kind of String"
    @str = str
  end
  def word_count
    out = Hash.new 0
    @str.downcase.split( /[ :,.!&@$%^&]+/ ).each{|w|
      out[w] += 1
    }
    return out
  end
end
