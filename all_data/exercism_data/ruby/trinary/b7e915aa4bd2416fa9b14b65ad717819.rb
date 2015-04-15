class Trinary < String
  def to_decimal
    return 0 unless valid?
    digits.reverse.each_with_index.reduce(0) do |base10,(digit,index)|
      base10 += digit * 3**index
    end
  end

  private
    def valid?
      self =~ /^[012]+$/
    end

    def digits
      chars.map &:to_i
    end
end
