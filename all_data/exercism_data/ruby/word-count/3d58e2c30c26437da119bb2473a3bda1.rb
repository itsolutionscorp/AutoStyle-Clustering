class Words

  attr_reader :words

  def initialize(input)
    @words = normalize(input)
  end

  def count
    counts = Hash.new(0)
    words.each do |word|
      counts[word] += 1
    end
    counts
  end

  private

  def normalize(input)
    input.downcase.gsub(/\W/, " ").split(" ")
  end


end
