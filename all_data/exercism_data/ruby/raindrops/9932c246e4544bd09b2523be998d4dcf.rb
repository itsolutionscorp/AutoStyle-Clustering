class Raindrops
  def self.convert(num)
    get_factors(num)
  end

  private

  def self.get_factors(num)
    output = ''
    output << 'Pling' if num % 3 == 0
    output << 'Plang' if num % 5 == 0
    output << 'Plong' if num % 7 == 0

    output == '' ? num.to_s : output
  end
end
