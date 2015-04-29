# ultimo rubocop
class Raindrops
  def convert(numero)
    res = ''
    if numero % 3 == 0
      res += 'Pling'
    elsif numero % 5 == 0
      res += 'Plang'
    elsif numero % 7 == 0
      res = 'Plang'
    elsif res == ''
      res = numero.to_s
    end
    res
  end
end
