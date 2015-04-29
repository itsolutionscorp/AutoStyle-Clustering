class Raindrops
  def convert (numero)
    res = ''
    if numero % 3 == 0
      res += 'Pling'
    end
    if numero % 5 == 0
      res += 'Plang'
    end
    if numero % 7 == 0
      res = 'Plang'
    end
    if res == ''
      res = numero.to_s
    end

    return res
  end
end

