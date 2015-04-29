# encoding: UTF-8
# Convierte los numeros primos de un numero a lenguaje raindrop
class Raindrops
  def convert(num)
    rsc = ''
    arr = conc_p(num)
    arr.each do |a|
      rs = get_raindrop_speak(a)
      rsc += rs.to_s
    end
    if rsc.length == 0
      return num.to_s
    else
      return rsc.to_s
    end
  end

  def conc_p(num)
    piv = 2
    arr = []
    while num != 1
      if num % piv == 0
        arr.push(piv) unless arr.include?(piv)
        num = num / piv
      else
        piv += 1
      end
    end
    arr
  end

  def get_raindrop_speak(num)
    case num
    when 3
      'Pling'
    when 5
      'Plang'
    when 7
      'Plong'
    else
      ''
    end
  end
end
