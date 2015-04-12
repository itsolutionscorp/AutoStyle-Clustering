class Phrase
  def initialize(phrase)
    raise ArgumentError, "Not valid - please create using a string" unless ( phrase.is_a? String )
    @phrase = phrase
  end

  def word_count
    counts = Hash.new
    parser.each do |word|
      if counts.keys.include?(word)
        counts[word] += 1
        next
      end
      counts[word] = 1
    end
    counts
  end

  private

  def parser
    @phrase.downcase.scan(/[\w\']+/).sort!
  end
end
