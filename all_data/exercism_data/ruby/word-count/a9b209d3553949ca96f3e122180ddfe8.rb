class Phrase

  def initialize(input)
    @words = input.downcase.scan(/[\w']+/)
  end

  def word_count
    Hash.new(0).tap do |counts|
      @words.each { |w| counts[w] += 1 }
    end
  end

end
