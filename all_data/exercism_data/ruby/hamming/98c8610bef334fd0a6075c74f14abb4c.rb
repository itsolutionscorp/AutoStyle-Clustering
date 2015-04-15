class Hamming
  def self.compute(first, second)
    length = first.length < second.length ? first.length : second.length

    length.times.reduce(0) do |result, index|
      result += 1 unless first[index] == second[index]
      result
    end
  end
end
