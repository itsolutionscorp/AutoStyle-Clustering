# Clase de granos por cada cuadro en un ajedrez
class Grains
  attr_reader :total_cuadros
  def initialize
    @total_cuadros = 64
  end
  # Potencia de 2 a la n-1
  def square(num_cuadro)
    2**(num_cuadro - 1)
  end
  # Total
  def total
    @total_cuadros.times do |i|
      self.total_granos += square(i)
    end
  end
end
