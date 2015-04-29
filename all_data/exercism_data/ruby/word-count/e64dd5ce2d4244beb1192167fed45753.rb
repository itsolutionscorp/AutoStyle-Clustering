class Phrase
  include Enumerable
  
  attr_reader :txt
  
  def initialize(input)
    @txt = input.downcase
  end
  
  def each(&block)
    txt.scan(/[[:word:]]+/).each { |word| yield(word) }
  end
  
  def word_count
    @word_count ||= inject({}) do |counts, word|
      counts.tap do
        old_count    = counts.fetch(word, 0)
        counts[word] = old_count + 1
      end
    end
  end
end
