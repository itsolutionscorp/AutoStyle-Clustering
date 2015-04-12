class Phrase

  def initialize(phrase)
    @words = phrase.downcase.split(/\W+/)
    @word_groups = @words.group_by {|w| w}
  end

  def word_count
    counts = {}
    @word_groups.each do |k, v|
      counts[k] = v.count
    end
    counts
  end

end
