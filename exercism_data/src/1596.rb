class Hamming
  def compute(strand1, strand2)
    braid = strand1.split('').zip(strand2.split(''))

    braid.reduce(0) do |distance, pair|
      pair.uniq.length == 1 ? distance : distance + 1
    end
  end

end
