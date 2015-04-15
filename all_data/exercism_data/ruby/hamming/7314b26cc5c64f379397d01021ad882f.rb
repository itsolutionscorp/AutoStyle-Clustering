class Hamming

  def self.compute(a, b)
    Hamming.new(a,b).difference
  end

  def initialize(a, b)
    @a = a
    @b = b
  end

  def difference
    return 0 if @a == @b
    compare_strands
  end

private

  def compare_strands
    replace_same_characters!('1')
    @a.count('1')
  end

  def replace_same_characters!(replacement)
    min_length = [@a.length, @b.length].min
    min_length.times do |i|
      unless @a[i] == @b[i]
        @a[i] = @b[i] = replacement
      end
    end
  end
end
