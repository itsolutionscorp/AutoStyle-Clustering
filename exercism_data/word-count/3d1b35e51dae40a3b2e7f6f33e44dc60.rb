class Phrase

  attr_reader :word_count

  def initialize( parse_me )
    @parse_me   = parse_me
    @word_count = tokenize( @parse_me )
  end

  private

  # Tokenize the input using the unicode aware character class [[:word:]]
  def tokenize( parse_me )
    counts = Hash.new(0)
    parse_me.scan( /[[:word:]]+/ ) do |token|
      counts[token.downcase] += 1
    end
    return counts
  end
end
