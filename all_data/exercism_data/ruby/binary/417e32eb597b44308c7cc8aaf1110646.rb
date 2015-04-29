Binary = Struct.new(:number) do

  def to_decimal
    return 0 if number =~ /[^10]/
    num_list = number.split(//).map { |s| s.to_i }
    results, pow = [], 0

    until num_list.empty?
      num = num_list.pop
      results << num * (2**pow)
      pow += 1
    end

    results.inject(:+)
  end
end
