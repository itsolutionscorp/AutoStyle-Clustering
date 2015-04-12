class Hamming
  def compute(original, replicant, i=0)
    shorter_strand = original.length < replicant.length ? original : replicant
    mutations = 0
    while i < shorter_strand.length
      mutations += 1 if original[i] != replicant[i]
      i += 1
    end

    mutations
  end
end
