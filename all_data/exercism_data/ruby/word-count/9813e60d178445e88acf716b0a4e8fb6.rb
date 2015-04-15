class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    return @word_count if instance_variable_defined? :@word_count
    @word_count = word_list.extend(Tabulate).tally_items
  end

private

  def normalized
    @phrase.gsub(/[^[:alnum:]]/, ' ').strip.downcase
  end

  def word_list
    normalized.split
  end

end



module Tabulate

  def tally_items
    self.inject(zeroed_tally) { |tally, item| add_item_to_tally(tally, item) }
  end

private

  def add_item_to_tally(tally, item)
    tally[item] += 1
    tally
  end

  def zeroed_tally
    Hash.new { 0 }
  end

end
