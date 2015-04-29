class Words

  attr_reader :sentence

  def initialize(input)
    @sentence = normalize(input)
  end

  def count
    counts = Hash.new(0)
    sentence.each do |word|
      counts[word] += 1
    end
    counts
  end

  private

  def normalize(input)
    input.downcase.gsub(/\W/, " ").split(" ")
  end


end
