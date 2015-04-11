class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    return @word_count ||= word_list.inject(Hash.new(0)) do |count, word|
      count[word.downcase] += 1
      count
    end
  end

  private
  def word_list
    return @phrase.gsub(/[[:punct:]]+/, ' ').split(/\s+/)
  end
end
