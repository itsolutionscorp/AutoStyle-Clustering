class Hamming

  def self.compute(s1, s2)
    s1 = s1.chars
    s2 = s2.chars
    mutations(s1, s2)
  end

  private

  def self.mutations(s1, s2)
    count = []
    compare_strands = s1.zip(s2)
    compare_strands.each do |pair|
      if pair.at(1) != nil && pair.at(1) != pair.at(0)
        count << pair
      end
    end
    count.size
  end
end
