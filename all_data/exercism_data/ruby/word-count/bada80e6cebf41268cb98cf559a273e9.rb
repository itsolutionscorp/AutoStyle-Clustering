class Phrase
  attr_reader :word_count
  def initialize(inphrase)
    @word_count = Hash.new(0)
    inphrase.downcase.split(/[^a-z0-9']+/).each do |w|
      @word_count[w] += 1
    end
  end
end
