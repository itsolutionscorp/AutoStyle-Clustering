class Hamming
  def compute(reference, mutation)
    reference.chars.zip(mutation.chars).count do |pair|
      pair[0] != pair[1]
    end
  end
end
