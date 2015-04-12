class Hamming
  def compute(one, other)
    one_bases = one.split("")
    other_bases = other.split("")

    zipped = one_bases.zip(other_bases)

    distance = 0

    zipped.each do |pair|
      next if pair.include?(nil)
      distance += 1 if pair.first != pair.last
    end

    distance
  end
end
