class Phrase
  attr_reader :word_count
  def initialize phrase
    @phrase = phrase
    @word_count = Hash.new(0)
    initialize_count
  end

  private
  def initialize_count
    @phrase.split(/[\s\,\:\!\&\@\$\%\^\.]/).map{|word| @word_count[word.downcase] += 1 unless word.empty?}
  end
end
