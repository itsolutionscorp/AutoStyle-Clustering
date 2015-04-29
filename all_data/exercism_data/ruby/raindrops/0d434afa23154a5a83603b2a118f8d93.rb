class Raindrops
  FACTOR_MAPPINGS = [
    {factor: 3, output: 'Pling'},
    {factor: 5, output: 'Plang'},
    {factor: 7, output: 'Plong'},
  ]

  def self.convert(number)
    @factors = find_factors(number)

    output = calculate_output

    if output.empty?
      number.to_s
    else
      output
    end
  end

  private
  def self.find_factors(num)
    [1, num] | (2..(num/2)).select { |i| (num % i).zero? }
  end

  def  self.calculate_output
    FACTOR_MAPPINGS.reduce('') { |memo, mapping| potentially_add_to_output(memo, mapping) }
  end

  def self.potentially_add_to_output(output, mapping)
    if @factors.include? mapping[:factor]
      output += mapping[:output]
    else
      output
    end
  end
end
