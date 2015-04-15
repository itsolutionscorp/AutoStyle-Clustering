module Parsable
  def parse_string input
    input.downcase.gsub(/\W+/, ' ').split(" ")
  end
end

class Phrase 
  include Parsable

  def initialize input
    @phrase = input
  end

  def word_count
    parsed = parse_string @phrase
    counts = Hash.new(0)
    parsed.each { |word| counts[word] += 1 }
    return counts
  end
end
