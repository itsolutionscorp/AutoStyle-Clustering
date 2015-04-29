class Binary
  def initialize(str)
    @str = str
  end
  
  def to_decimal
    @str.chars.inject(0) do |v,c|
      return 0 unless %w(0 1).include? c
      v += v + c.to_i
    end
  end
end
