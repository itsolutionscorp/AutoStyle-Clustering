class Hamming
  def compute(*strands)
    strands.map(&:chars).transpose.count do |set|
      set.uniq.length > 1
    end
  end
end
