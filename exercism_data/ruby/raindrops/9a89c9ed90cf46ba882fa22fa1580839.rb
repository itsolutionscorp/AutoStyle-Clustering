# = raindrops.rb
# Author::     Ricardo Gonzalez Saldivar
# Web::        rigsald.net
# == Raindrops Class
# _Raindrops_ class was designed during the
# exercism.io exercises.
class Raindrops
  def convert(n)
    res = ''
    res = "#{res}Pling" if n % 3 == 0
    res = "#{res}Plang" if n % 5 == 0
    res = "#{res}Plong" if n % 7 == 0
    res = n.to_s if res == ''
    res
  end
end
