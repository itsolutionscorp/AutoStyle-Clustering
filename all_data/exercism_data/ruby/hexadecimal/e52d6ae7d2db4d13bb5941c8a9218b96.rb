class Hexadecimal

  attr_reader :hex

  HXD = { "a" => 10, "b" => 11, "c" => 12,
          "d" => 13, "e" => 14, "f" => 15 }

  def initialize hex
    @hex = hex
  end

  def to_decimal
    return 0 if !!(hex =~ /[g-z]/)

    hexs.each.with_index.inject(0) do |sum,(n,i)|
      sum += 16 ** i * n
    end
  end

  private

  def hexs
    hex.chars.map { |e| HXD.fetch(e, e).to_i }.reverse
  end

end
