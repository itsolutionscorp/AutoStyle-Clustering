class Words
  attr_reader :words

  def initialize(input)
    @words = input.downcase.scan(/\w+/)
  end

  def count
    counts = Hash.new(0)

    words.each do |words|
      counts[words] += 1
    end
    counts
  end
end
