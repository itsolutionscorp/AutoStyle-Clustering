class Fixnum
  INTERVALS = { 1 => 'I',  5 => 'V', 10 => 'X', 50 => 'L', 100 => 'C', 500 => 'D', 1000 => 'M'}
  PREFIXES = [1, 10, 100]

  def to_roman(remainder = self, output = '', high = 1000)
    return output if remainder == 0

    return to_roman(remainder - high, output + INTERVALS[high], high) if remainder >= high

    pre = prefix(high)
    num = high - pre
    return to_roman(remainder - num, output + INTERVALS[pre] + INTERVALS[high], high) if remainder >= num

    high = next_interval(high)
    to_roman(remainder, output, high)
  end

  private

  def next_interval(int)
    INTERVALS.keys.sort.select { |x| x < int }.last
  end

  def prefix(val)
    PREFIXES.sort.select { |x| x < val }.last
  end


end
