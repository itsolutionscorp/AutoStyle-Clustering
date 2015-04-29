class Hamming

  def self.compute(original, mutation)
    original.chars.zip(mutation.chars).count do |parent, child|
      parent != child && !parent.nil? && !child.nil?
    end
  end
end
