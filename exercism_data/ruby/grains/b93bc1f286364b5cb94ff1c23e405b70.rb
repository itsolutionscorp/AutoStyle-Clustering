# = grains.rb
# Author::     Ricardo Gonzalez Saldivar
# Web::        rigsald.net
#
# == Grains Class
# _Grains_ class was designed during the
# exercism.io exercises.
class Grains
  def square(n)
    2**(n - 1)
  end

  def total
    (square 65) - 1
  end
end
