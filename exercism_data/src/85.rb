class Phrase
  def initialize(input)
    @words = input.downcase.scan /\w+/
  end

  def word_count
    @counts ||= Hash.new(0).tap do |counts|
      @words.each { |word| counts[word] += 1 }
    end
  end
end
