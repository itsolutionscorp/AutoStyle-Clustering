class Hamming
  def compute(sequence1, sequence2)
    sequence1.each_char.zip(sequence2.each_char).count do |a, b|
      a != b && !a.nil? && !b.nil?
    end
  end
end
