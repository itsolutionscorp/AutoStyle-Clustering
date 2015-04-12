class Phrase
  attr_accessor :phrase, :counts

  def initialize(phrase)
    @phrase = []
    phrase.downcase.split(/\s|[,]/).each do |word|
      word.include?('\'') ? @phrase << word : @phrase << word.gsub(/\W+/, "")
    end
  end

  def word_count
    @counts = Hash.new(0)
    @phrase.each { |word| @counts[word] += 1 if !word.empty? }
    return @counts
  end
end
