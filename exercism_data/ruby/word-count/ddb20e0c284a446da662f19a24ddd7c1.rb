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
    parsed.each_with_object(Hash.new(0)) { |word, hsh| hsh[word] += 1 }
  end
end
