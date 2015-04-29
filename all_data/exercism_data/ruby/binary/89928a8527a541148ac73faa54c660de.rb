Binary = Struct.new(:string) do
  def to_decimal
    return 0 if string =~ /[^01]/

    string.reverse.each_char.with_index.reduce(0) do |result, (char, i)|
      char == '1' ? result + 2**i : result
    end
  end
end
