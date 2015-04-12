class Phrase
  attr_reader :original_phrase

  def initialize(string)
    @original_phrase = string
  end

  def word_count
    prep = original_phrase.gsub(/\s/, ',')
                          .split(',')
                          .map { |w| w.match(/[A-Za-z0-9']+/).to_s.downcase }
                          .delete_if { |w| w.empty? }
    @histogram ||= prep.each_with_object(Hash.new(0)) { |e, a| a[e] += 1 }
  end
end
