class Hamming
  def self.compute(first_strand, second_strand)
    distance = 0
    individual_acids(first_strand) do |acid, position|
      next if second_strand[position].nil?
      distance += 1 unless acids_equal?(acid, second_strand[position])
    end
    distance
  end

  def self.individual_acids(strand, &block)
    strand.chars.each_with_index do |acid, position|
      yield(acid, position) if block_given?
    end
  end

  def self.acids_equal?(acid1, acid2)
    acid1 == acid2
  end
end
