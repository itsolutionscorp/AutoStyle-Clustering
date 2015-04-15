class Hamming
  def self.compute( strand_a, strand_b )
    gene_index, hamming_distance = 0, 0

    while strand_a[ gene_index ] && strand_b[ gene_index ]
      hamming_distance += 1 if strand_a[ gene_index ] != strand_b[ gene_index ]
      gene_index += 1
    end

    hamming_distance
  end
end
