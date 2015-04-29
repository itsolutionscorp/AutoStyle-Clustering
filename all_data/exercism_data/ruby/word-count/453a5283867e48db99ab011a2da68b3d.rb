class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= word_list.extend(Tabulatable).tally_items
  end

private

  def word_list
    @phrase.downcase.scan(/\w+/)
  end

end



module Tabulatable

  def tally_items
    self.each_with_object(zeroed_tally) { |item, tally| tally[item] += 1 }
  end

private

  def zeroed_tally
    Hash.new { 0 }
  end

end
