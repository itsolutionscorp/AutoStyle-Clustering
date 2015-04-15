class DNA

  def initialize(sequence)
    sequence.chars.each do |base|
      raise ArgumentError unless counts.key?(base)
      counts[base] += 1
    end
  end

  def count(base)
    counts[base] or raise ArgumentError
  end

  def nucleotide_counts
    counts.clone
  end

private

  def counts
    @counts ||= %w(A C G T).each_with_object({}) { |base, hash| hash[base] = 0 }
  end

end
