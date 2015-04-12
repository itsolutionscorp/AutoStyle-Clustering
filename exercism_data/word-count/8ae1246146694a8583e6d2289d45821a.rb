class Phrase

  def initialize( text )
    @word_count = Hash.new { |h,k| h[k] = 0 }
    parse_text( text )
  end

  attr_reader :word_count

  private

  def parse_text( text )
    text.split(/[\s,]+/).each do |word|
      real_word = /^([a-z']+|[0-9]+)/.match( word.downcase ).to_s

      next if real_word == ""

      @word_count[ real_word ] += 1
    end
  end

end
