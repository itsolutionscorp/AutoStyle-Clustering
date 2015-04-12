# Word counting class.
class Phrase
  attr_reader :word_count

  def initialize( phrase )
    @word_count = Hash.new( 0 )

    filtered( phrase ).split.each { |word| @word_count[word] += 1 }
  end

  def filtered( phrase )
    phrase
      .downcase
      .gsub( /[,."!&@%^$:;]/, ' ' )
  end
end
