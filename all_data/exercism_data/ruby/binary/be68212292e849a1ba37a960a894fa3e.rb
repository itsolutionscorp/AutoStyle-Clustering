class Binary
  def initialize binary
    @binary = binary
  end
  def to_decimal
    return 0 if @binary.alpha?

    unit = 1
    @binary.split('').reverse.reduce(0) do |decimal, i|
      decimal += unit if i == '1'
      unit *= 2
      decimal
    end
  end
end

class String
  def alpha?
    !!match(/[:alpha:]/)
  end
end
