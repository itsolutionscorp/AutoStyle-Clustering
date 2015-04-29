class Binary
  def initialize(string)
    @string = string
  end

  def to_decimal
    return 0 unless valid?
    @string.chars.inject(0) do |sum, char|
      sum *= 2
      sum + char.to_i
    end
  end

  private

  def valid?
    @string =~ /\A[01]+\z/
  end
end
