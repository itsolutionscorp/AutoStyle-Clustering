class Words

  attr_reader :words

  def initialize(input)
    @words = normalize(input)
  end

  def count
    word_counts = Hash.new(0)
    words.each do |word|
      word_counts[word] += 1
    end
    word_counts
  end

  private

  def normalize(input)
    input.downcase.gsub(/\W/, " ").split(" ")
  end


end
