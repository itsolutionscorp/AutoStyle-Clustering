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
    @word_count ||= each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end
end
