class Binary
  def initialize(binary)
    @binary = binary
  end

  def to_decimal
    return 0 unless valid?

    @binary.chars.map.with_index do |char, idx|
      char.to_i * (2 ** (@binary.length - idx - 1))
    end.inject(:+)
  end

  private
  def valid?
    @binary.match(/^(0|1)+$/).nil? ? false : true
  end

end
