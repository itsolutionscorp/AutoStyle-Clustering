class Grains
  def square(x)
    generate_values
    get_value(x)
  end

  def total
    generate_values
    @list.inject(0) { |a, e| a + e }
  end

  def generate_values
    @list ||= [*(2..64)].inject([1]) do |result|
      result << result.last * 2
    end
    @list
  end

  def get_value(x)
    @list[x - 1]
  end

end
