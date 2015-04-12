class Phrase

  attr_reader :input

  def initialize(input)
    @input = input
  end

  def word_count
    words = clean_split_prep(input)
    counts = Hash.new(0)
    words.each do |word|
      counts[word] += 1 unless word.empty?
    end
    return counts
  end

  def clean_split_prep(input)
    input.downcase.split(/[\W]/)
  end

end
