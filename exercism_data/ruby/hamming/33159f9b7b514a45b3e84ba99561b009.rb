class Hamming
  
  def self.compute d1, d2
    Hamming.new(d1, d2).distance
  end

  def initialize d1, d2
    @d1 = d1
    @d2 = d2
  end

  def distance
    hamming_distance
  end

  private

    def hamming_distance
      differences = 0
      @d1.chars.each_with_index do |char, index|
        differences += 1 if char != @d2[index]
      end
      differences
    end
end
