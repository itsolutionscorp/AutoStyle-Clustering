class Trinary < String
  def to_decimal
    return 0 unless valid?
    max_index = length - 1
    chars.each_with_index.reduce(0) do |base10,(char,index)|
      base10 + ( get_value(char) * 3**(index - max_index).abs )
    end
  end

  private
    def valid?
      self =~ /^[012]+$/
    end

    def get_value char
      char.ord - '0'.ord
    end
end
