class Raindrops
  def self.convert(number)
    my_string = ''
    my_string << 'Pling' if number.to_i % 3 == 0
    my_string << 'Plang' if number.to_i % 5 == 0
    my_string << 'Plong' if number.to_i % 7 == 0
    my_string = number.to_s if my_string.empty?
    my_string
  end
end
