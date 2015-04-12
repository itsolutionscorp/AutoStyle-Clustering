require 'byebug'

class Hamming
  def compute(x, y)
    data = x.split('').zip y.split('')
    data.collect { |a, b| a == b }.count(false)
  end
end