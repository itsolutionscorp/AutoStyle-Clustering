class Phrase
  attr_accessor :phrase
  def initialize(phrase="hello world")
    @phrase = normalize_phrase(phrase)
  end
  def word_count
    @count_hash = Hash.new
    @word_array = @phrase.split(/[\s,:!@\$%\^&\.]/).delete_if {|x| x.empty? }
    @word_array.each do |k|
      @count_hash[k] = (@count_hash[k].nil?) ? 1 : @count_hash[k]+1
    end
    @count_hash
  end
  private
  def normalize_phrase(phrase)
    phrase.downcase
  end
end
