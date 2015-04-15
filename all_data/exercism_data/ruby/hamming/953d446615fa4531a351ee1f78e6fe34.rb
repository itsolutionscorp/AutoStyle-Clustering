class Hamming
  def self.compute(foo, bar)
    foo, bar = bar, foo if bar.size < foo.size
    difference = 0

    foo.each_char.with_index do |char, index|
      difference += 1 if char != bar[index]
    end

    difference
  end
end
