class Hamming
  def self.compute(first,second)
    len = first.length < second.length ? first.length : second.length
    (0...len).inject(0) { |memo,i|
      memo += 1 if first[i] == second[i]
    }
  end
end
