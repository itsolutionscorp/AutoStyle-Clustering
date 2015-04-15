class Hamming
  def self.compute(original, compare)
    differences = 0

    @longer = original.size >= compare.size ? original : compare
    @shorter = original.size >= compare.size ? compare : original

    @shorter.each_char.with_index do |char, index|
      differences += 1 unless char == @longer[index]
    end
    differences
  end
end
