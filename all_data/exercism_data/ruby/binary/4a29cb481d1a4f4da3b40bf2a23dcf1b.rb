# class to convert binary to decimal
class Binary
  def initialize(bstring)
    @bstring = bstring
  end

  def to_decimal
    return 0 unless @bstring.gsub(/[0|1]/, '').empty?
    l = @bstring.length
    @bstring.chars.map { |c| c.to_i * (2**(l -= 1)) }.reduce(&:+)
  end
end
