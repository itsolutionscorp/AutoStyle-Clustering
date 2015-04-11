class Hamming
  def self.compute(first, last)
    short, long = [first,last].sort_by!(&:size)
    short.chars.zip(long.chars).inject(0) do |distance, nucleotides|
      if nucleotides.first == nucleotides.last
        distance
      else
        distance + 1
      end
    end
  end
end
