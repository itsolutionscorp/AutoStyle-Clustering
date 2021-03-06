class Hamming
  def self.compute(original, replicated)
    min_of_original_replicated = [original.length, replicated.length].min-1

    (0..min_of_original_replicated).count do |i|
      original[i] != replicated[i]
    end
  end
end
