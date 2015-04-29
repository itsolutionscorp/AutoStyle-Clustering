def compute(strand1, strand2)
    result = 0
    shorter, other = [strand1, strand2].sort { |a, b| a.length <=> b.length }
    shorter.split('').each_with_index do |point, i|
      result += point == other.split('')[i] ? 0 : 1
    end
    result
  end